package com.gamers.Beans;

import com.gamers.Entities.Group;
import com.gamers.Entities.Message;
import com.gamers.Entities.Person;
import com.gamers.Services.PersonService;
import com.google.common.hash.Hashing;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;

@Path("registration")
@Stateless
@Local(Registration.class)
public class RegistrationBean implements Registration, Serializable {

    private PersonService personService = new PersonService();

    @POST
    @Path("/new")
    @Override
    public void register(@FormParam("nickname") String nickname,
                         @FormParam("password") String password,
                         @FormParam("email") String email)
    {
        Person person = new Person();
        Group group = new Group("user");
        person.setEmail(email);
        person.setNickname(nickname);
        person.setPassword( SHA256(password) );
        person.addGroup(group);
        personService.create(person);

    }

    private String SHA256(String password)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }
        catch (Exception e) {
            return null;
        }
    }

}
