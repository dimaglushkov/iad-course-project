package com.gamers.Beans;


import com.gamers.DAO.InfoDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Info;
import com.gamers.Entities.Person;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.sql.Date;

@Stateless
@Path("info")
@Local(InfoInterface.class)
public class InfoBean implements InfoInterface
{

    @Resource
    SessionContext sessionContext;

    private JSONObject response;
    private PersonDAO personDAO = new PersonDAO();

    private InfoDAO infoDAO = new InfoDAO();


    @POST
    @Path("set")
    @Produces("application/json")
    @RolesAllowed({"admin","user"})
    @Override
    public JSONObject setInfo(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("birthDate") String birthDateStr, @FormParam("country") String country, @FormParam("city") String city)
    {
        Person curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        response = new JSONObject();

        if (curPerson == null)
            return initResponse(false, "Wrong nickname");

        Date birthDate;
        try
        {
            birthDate = Date.valueOf(birthDateStr);
        }
        catch (IllegalStateException e)
        {
            return initResponse(false, "Wrong date format");
        }

        Info info = new Info();
        info.setPerson(curPerson);
        info.setBirthDate(birthDate);

        if (name != null)
            info.setName(name);

        if (surname != null)
            info.setName(surname);

        if (country != null)
            info.setCountry(country);

        if (city != null)
            info.setCity(city);

        Info oldInfo = infoDAO.findByPersonId(curPerson.getId());
        info.setRegisterDate(oldInfo.getRegisterDate());

        infoDAO.delete(oldInfo);
        infoDAO.create(info);
        return initResponse(true, "Info created");
    }

    @GET
    @Path("{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin","user"})
    @Override
    public JSONObject getInfo(@PathParam("nickname") String nickname)
    {
        response = new JSONObject();
        Person person = personDAO.findByNickname(nickname);

        if (person == null)
            return initResponse(false, "Wrong nickname");

        Info info = infoDAO.findByPersonId(person.getId());
        JSONObject infoObj = new JSONObject();

        infoObj.put("id", info.getId());
        infoObj.put("personId", person.getId());

        if (info.getBirthDate() != null)
            infoObj.put("birthDate", info.getBirthDate().toString());

        if (info.getCity() != null)
            infoObj.put("city", info.getCity());

        if (info.getCountry() != null)
            infoObj.put("country", info.getCountry());

        if (info.getName() != null)
            infoObj.put("name", info.getName());

        if (info.getSurname() != null)
            infoObj.put("surname", info.getSurname());

        infoObj.put("registrationDate", info.getRegisterDate().toString());
        response.put("info", infoObj);
        return initResponse(true, "Info found");
    }

    private JSONObject initResponse(Boolean success, String desc)
    {
        response.put("success", success.toString());
        response.put("description", desc);
        return response;
    }

}
