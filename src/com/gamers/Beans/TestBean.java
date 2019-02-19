package com.gamers.Beans;

import com.gamers.Entities.Person;
import com.gamers.DAO.PersonDAO;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/test")
//@Local(TestBean.class)
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TestBean
{

    @Resource
    private SessionContext context;
    private Person currentPerson;


    private PersonDAO personDAO = new PersonDAO();

    @GET
    @Path("main")
    public Response welcome(@Context HttpServletResponse resp, @Context HttpServletRequest req){

        Person person = personDAO.findByNickname("root");
        return  Response.status(Response.Status.OK).entity(person.getEmail()).build();

    }

    @GET
    @Path("hello")
    public Response testing(@Context HttpServletResponse resp, @Context HttpServletRequest req){
        return  Response.status(Response.Status.OK).entity("Hello!").build();
    }

    @GET
    @Path("test")
    public Response test(@Context HttpServletResponse resp, @Context HttpServletRequest req){

        StringBuilder result = new StringBuilder("admins: ");
        List<Person> personList = personDAO.findByGroupName("admin");

        for (Person person : personList) {
            currentPerson = person;
            result.append(currentPerson.getNickname());
        }

        /*currentPerson = personDAO.findByNickname(context.getCallerPrincipal().getName());
        String response = currentPerson.getNickname();*/
        return  Response.status(Response.Status.OK).entity(result.toString()).build();

    }





}
