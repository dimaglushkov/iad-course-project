package com.gamers.Beans;

import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Person;

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


    private PersonDAO personDAO = new PersonDAO();
    private Person person = new Person();

    @GET
    @Path("main")
    public Response welcome(@Context HttpServletResponse resp, @Context HttpServletRequest req){



        person.setEmail("is3@it.working");
        person.setNickname("testnickname3.5");
        person.setPassword("mypassword");
        personDAO.save(person);

        return  Response.status(Response.Status.OK).entity(person.getNickname()).build();

    }

    @GET
    @Path("")
    public Response testing(@Context HttpServletResponse resp, @Context HttpServletRequest req){
        return  Response.status(Response.Status.OK).entity("Hello!").build();
    }





}
