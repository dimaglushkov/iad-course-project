package com.boatguys.buisiness;

import com.boatguys.data.UserDAO;
import com.boatguys.entities.User;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("users/profile")
@Stateful
@Local
public class UserProfileBean implements UserProfile
{
    @Resource
    private SessionContext context;

    private UserDAO userDAO = new UserDAO();

    private User currentUser;
    private Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy()
    {
        @Override
        public boolean shouldSkipField(FieldAttributes f)
        {
            return (f.getDeclaringClass() == User.class &&
                    ( f.getName().equals("password") || f.getName().equals("boats")));
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass)
        {
            return false;
        }
    }).create();

    public void initCurrUser()
    {
        currentUser = userDAO.getByEmail(context.getCallerPrincipal().getName());
    }

    @GET
    @Produces("application/json")
    @Path("/info")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getInformation()
    {
        initCurrUser();

        return gson.toJson(currentUser);
    }

    @POST
    @Path("/change")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void change(@FormDataParam("name") String name, @FormDataParam("gender") String gender, @FormDataParam("password") String password)
    {
        initCurrUser();

        if(!(gender.equals("М") || gender.equals("Ж")))
            throw new IllegalArgumentException("No such gender! We support only classic genders");

        currentUser.setName(name);
        currentUser.setGender(gender.charAt(0));
        currentUser.setPassword(password);

        userDAO.update(currentUser);
    }

    @POST
    @Path("/logout")
    @RolesAllowed({"users", "administrators", "moderators"})
    public void logout(@Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException
    {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

}



