package com.gamers.Beans;

import com.gamers.Entities.Group;
import com.gamers.Entities.Person;
import com.gamers.Services.PersonService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.Serializable;

@Path("registration")
@Stateless
@Local(Registration.class)
public class RegistrationBean implements Registration, Serializable {

    private PersonService personService = new PersonService();

    @POST
    @Path("/new")
    @Override
    public void register(@FormParam("email") String email,
                         @FormParam("password") String password,
                         @FormParam("name") String name)
    {
        Person person = new Person();
        Group group = new Group("user");
        person.setEmail(email);
        person.setNickname(name);
        person.setPassword(password);
        person.addGroup(group);

        personService.create(person);

    }

}
