package com.gamers.Beans;

import com.gamers.Services.DAOService;
import com.gamers.Entities.Person;
import com.gamers.Services.PersonService;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/")
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserBean {

    //TODO: авторизация и security через glassfish
    //TODO: подключить модули angular
    //TODO: jms - какая то логика
    //TODO: продумать функционал
    //TODO: продумать апи
    //TODO: дописать дао-сервисы
    //TODO: дописать бины
    //TODO: дописать фронт

    private PersonService personService = new PersonService();

    @GET
    @Path("main")
    public Response welcome(@Context HttpServletResponse resp, @Context HttpServletRequest req){

        Person person = personService.findByNickname("somename");

        return  Response.status(Response.Status.OK).entity(person.getEmail()).build();

    }

    @GET
    @Path("")
    public Response testing(@Context HttpServletResponse resp, @Context HttpServletRequest req){
        return  Response.status(Response.Status.OK).entity("Hello!").build();
    }





}
