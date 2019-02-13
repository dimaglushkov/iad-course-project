package com.boatguys.buisiness;

import com.boatguys.data.BaseBoatDAO;
import com.boatguys.data.DetailDAO;
import com.boatguys.data.ProducerDAO;
import com.boatguys.entities.BaseBoat;
import com.boatguys.entities.Detail;
import com.boatguys.entities.Producer;
import com.google.gson.Gson;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;

@Path("producers")
@Stateless
@Local(ProducersInfo.class)
public class ProducersInfoBean implements ProducersInfo
{
    @Resource
    private SessionContext context;

    private ProducerDAO producerDAO = new ProducerDAO();
    private DetailDAO detailDAO = new DetailDAO();
    private BaseBoatDAO baseBoatDAO = new BaseBoatDAO();

    private Gson gson = new Gson();

    @GET
    @Produces("application/json")
    @Path("/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getAllProducers()
    {
        List<Producer> producers = producerDAO.getAll();

        return gson.toJson(producers);
    }

    @GET
    @Produces("application/json")
    @Path("/{producerId}/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getDetailsFromProducer(@PathParam("producerId") long producerId)
    {
        Producer producer = producerDAO.getByID(producerId);

        return gson.toJson(producer.getDetails());
    }

    @GET
    @Produces("application/json")
    @Path("/boats/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getAllBaseBoats()
    {
        List<BaseBoat> baseBoats = baseBoatDAO.getAll();

        return gson.toJson(baseBoats);
    }

    @POST
    @Path("/details/new")
    @RolesAllowed({"producers"})
    @Override
    public void addDetail(@FormDataParam("type") String type,
                   @FormDataParam("weight") int weight,
                   @FormDataParam("speedEffect") int speedEffect,
                   @FormDataParam("controllabilityEffect") int controllabilityEffect,
                   @FormDataParam("boostEffect") int boostEffect,
                   @FormDataParam("price") int price)
    {
        Producer producer = producerDAO.getByEmail(context.getCallerPrincipal().getName());

        Detail newDetail = new Detail(type, weight, speedEffect, controllabilityEffect, boostEffect, price);

        detailDAO.save(newDetail);

        producer.addDetail(newDetail);

        producerDAO.update(producer);
    }

    @POST
    @Path("/details/delete/{detailId}")
    @RolesAllowed({"administrators", "producers"})
    @Override
    public void removeDetail(@PathParam("detailId") long detailId)
    {
        Producer producer = producerDAO.getByEmail(context.getCallerPrincipal().getName());

        Detail detail = detailDAO.getByID(detailId);

        producer.getDetails().remove(detail);

        producerDAO.update(producer);
        detailDAO.delete(detail);
    }

    @POST
    @Path("/new")
    @RolesAllowed({"producers"})
    @Override
    public void addNewProducer(@FormDataParam("name") String name,
                        @FormDataParam("phoneNumber") String phoneNumber,
                        @FormDataParam("webSite") String webSite)
    {
        String email = context.getCallerPrincipal().getName();

        Producer newProducer = new Producer(name, webSite, phoneNumber, email);

        producerDAO.save(newProducer);
    }


}
