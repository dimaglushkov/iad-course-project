package com.boatguys.buisiness;

import com.boatguys.data.UserDAO;
import com.boatguys.entities.Group;
import com.boatguys.entities.User;
import com.google.gson.Gson;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.io.Serializable;
import java.util.Date;

@Path("users/registration")
@Stateless
@Local(UserRegistration.class)
public class UserRegistrationBean implements UserRegistration, Serializable
{
    private UserDAO userDao = new UserDAO();
    private Group userGroup = new Group("users");
    private Gson gson = new Gson();

    @POST
    @Path("/new")
    @Override
    public void register(@FormParam("email") String email,
                  @FormParam("password") String password,
                  @FormParam("name") String name)
    {
        if(hasSuchUser(email))
            throw new IllegalArgumentException("User with email "+email+" is already exist");

        User newUser = new User();

        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setBirthday(new Date());
        newUser.setBalance(0);
        newUser.setRate(0);
        newUser.setSportRate(0);
        newUser.setGender('М');
        newUser.addGroup(userGroup);



        userDao.save(newUser);

        Mail mail = new Mail();

        mail.send("Registration completed", "Welcome to our club!", email);
    }

    @GET
    @Produces("application/json")
    @Path("/exist/{email}")
    @Override
    public String hasUserWithSuchEmail(@PathParam("email") String email)
    {
        return gson.toJson(hasSuchUser(email));
    }

    private boolean hasSuchUser(String email)
    {
        return userDao.hasUserWithSuchEmail(email);
    }
}
