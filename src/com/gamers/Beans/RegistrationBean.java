package com.gamers.Beans;

import com.gamers.Entities.Group;
import com.gamers.Entities.Person;
import com.gamers.DAO.PersonDAO;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;

@Path("registration")
@Stateless
@Local(Registration.class)
public class RegistrationBean implements Registration, Serializable
{

    private PersonDAO personDAO = new PersonDAO();

    @POST
    @Path("/new")
    @Override
    public JSONObject register(@FormParam("nickname") String nickname,
                             @FormParam("password") String password,
                             @FormParam("email") String email)
    {

        JSONObject responseJSON = new JSONObject();
        Person person = new Person();
        Group group = new Group("user");

        if (isPersonWithSuchNicknameOrEmailExists(nickname, email))
        {
            responseJSON.put("success", "false");
            responseJSON.put("description", "User with such nickname or email already exists.");

            return responseJSON;
        }

        try
        {
            person.setEmail(email);
            person.setNickname(nickname);
            person.setPassword(SHA256(password));
            person.addGroup(group);
            personDAO.create(person);
        }
        catch (Exception e)
        {
            responseJSON.put("success", "false");
            responseJSON.put("description", e.getMessage());
            return responseJSON;
        }

        responseJSON.put("success", "true");
        responseJSON.put("description", "Profile created.");


        return responseJSON;
    }

    private String SHA256(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(no.toString(16));

            while (hashtext.length() < 32)
            {
                hashtext.insert(0, "0");
            }

            return hashtext.toString();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private boolean isPersonWithSuchNicknameOrEmailExists(String nickname, String email)
    {
        Person person = personDAO.findByNickname(nickname);
        if (person != null)
            return true;

        person = personDAO.findByEmail(email);
        if (person != null)
            return true;

        return false;

    }



}
