package com.gamers.Beans;

import com.gamers.DAO.GenericDao;
import com.gamers.Entities.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Stateless
@Path("/")
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class HandlerBean {


    private GenericDao<Person, Long> personService = new GenericDao<>(Person.class);


    @GET
    @Path("main")
    public Response welcome(@Context HttpServletResponse resp, @Context HttpServletRequest req){

        Person person = new Person();

        person.setNickname("no criteria API");
        person.setPassword("no criteria API");
        person.setEmail("no criteria API");
        personService.create(person);

        return  Response.status(Response.Status.OK).entity(person.getNickname()).build();

    }

    @GET
    @Path("")
    public Response testing(@Context HttpServletResponse resp, @Context HttpServletRequest req){
        return  Response.status(Response.Status.OK).entity("Hello!").build();
    }





}
