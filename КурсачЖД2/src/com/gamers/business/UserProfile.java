package com.gamers.business;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;
import java.util.Date;

@Path("users/profile")
@Local
public interface UserProfile
{
    @GET
    @Produces("application/json")
    @Path("/")
    @RolesAllowed({"users", "administrators", "moderators"})
    String getInformation();

    @POST
    @Path("/change")
    @RolesAllowed({"users", "administrators", "moderators"})
    void change(@QueryParam("name") String name,@QueryParam("birthday") Date birthday,@QueryParam("gender") String gender,@QueryParam("password") String password);
}
