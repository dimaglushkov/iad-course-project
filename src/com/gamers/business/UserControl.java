package com.gamers.business;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;

@Path("users")
@Local
public interface UserControl
{
    @GET
    @Produces("application/json")
    @Path("/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    String getAllUsers();

    @POST
    @Path("/ban/{username}")
    @RolesAllowed({"administrators"})
    void banUser(@PathParam("username") String username);

    @POST
    @Path("/unban/{username}")
    @RolesAllowed({"administrators"})
    void unbanUser(@PathParam("username") String username);

    @POST
    @Path("/grant/{username}/{groupName}")
    @RolesAllowed({"administrators"})
    void grantRights(@PathParam("username") String username,@PathParam("groupName") String groupName);

    @POST
    @Path("/ungrant/{username}/{groupName}")
    @RolesAllowed({"administrators"})
    void takeRightsAway(@PathParam("username") String username,@PathParam("groupName") String groupName);
}
