package com.gamers.Beans;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Startup
@Stateless
@Path("/")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

public class HandlerBean {



    @GET
    @Path("main")
    @Produces(MediaType.TEXT_HTML)
    public Response welcome(@Context HttpServletResponse resp, @Context HttpServletRequest req){
        return  Response.status(Response.Status.OK).entity("Hello!").build();
    }
}
