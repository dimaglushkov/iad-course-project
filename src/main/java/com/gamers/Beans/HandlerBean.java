package com.gamers.Beans;

import com.gamers.DAO.GenericDAO;
import com.gamers.DAO.GenericDAOImpl;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Game;
import com.gamers.Entities.Person;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Stateless
@Path("/")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class HandlerBean {

    //@EJB
    //private PersonDAO personDAO;

    @GET
    @Path("main")
    public Response welcome(@Context HttpServletResponse resp, @Context HttpServletRequest req){

        //String email = personDAO.findByNickname("somename").getEmail();
        return  Response.status(Response.Status.OK).entity("message").build();

        /*Person person = personDAO.findById(0L);
        String username = person.getNickname();*/

    }

    @GET
    @Path("")
    public Response testing(@Context HttpServletResponse resp, @Context HttpServletRequest req){
        return  Response.status(Response.Status.OK).entity("Hello!").build();
    }





}
