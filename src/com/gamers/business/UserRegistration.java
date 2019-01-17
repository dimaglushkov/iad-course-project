package com.gamers.business;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ws.rs.*;

@Path("users/registration")
@Local
public interface UserRegistration
{
    @Path("/new")
    void register(@FormParam("email") String email,
                  @FormParam("password") String password,
                  @FormParam("name") String name);


    @Produces("application/json")
    @Path("/exist/{email}")
    String hasUserWithSuchEmail(String email);

}
