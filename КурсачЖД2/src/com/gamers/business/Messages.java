package com.gamers.business;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;

@Path("messages")
@Local
public interface Messages
{
    @GET
    @Produces("application/json")
    @Path("/{username}")
    @RolesAllowed({"users", "administrators", "moderators"})
    String getDialogWithUser(@PathParam("username") String username);

    @POST
    @Path("/send/{username}")
    @RolesAllowed({"users", "administrators", "moderators"})
    void send(@QueryParam("message") String message,@PathParam("username") String usernameOfReciever);

    @GET
    @Produces("application/json")
    @Path("/last")
    @RolesAllowed({"users", "administrators", "moderators"})
    String getLastUserMessages();
}
