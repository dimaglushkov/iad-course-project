package com.boatguys.buisiness;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Date;


@Local
public interface UserProfile
{

    @RolesAllowed({"users", "administrators", "moderators"})
    String getInformation();


    @RolesAllowed({"users", "administrators", "moderators"})
    void change(String name, String gender, String password);


	@RolesAllowed({"users", "administrators", "moderators"})
    public void logout(@Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException;
}
