package com.gamers.business;

import com.gamers.data.UserDAO;
import com.gamers.entities.User;
import com.google.gson.Gson;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.Serializable;
import java.util.Date;

@Stateless
@Local(UserRegistration.class)
public class UserRegistrationBean implements UserRegistration, Serializable
{
    private UserDAO userDao = new UserDAO();

    private Gson gson = new Gson();

    @POST
    @Override
    public void register(String email, String password, String name) throws IllegalArgumentException
    {
        if(hasSuchUser(email))
            throw new IllegalArgumentException("User with email "+ email +" is already exist");

        User newUser = new User();

        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setBirthday(new Date());

        userDao.save(newUser);

        Mail mail = new Mail();

        mail.send("Registration completed", "Welcome to our club!", email);
    }

    @GET
    @Override
    public String hasUserWithSuchEmail(String email)
    {
        return gson.toJson(hasSuchUser(email));
    }

    private boolean hasSuchUser(String email)
    {
        return userDao.hasUserWithSuchEmail(email);
    }
}
