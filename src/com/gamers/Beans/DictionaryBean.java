package com.gamers.Beans;


import com.gamers.DAO.DictionaryDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Dictionary;
import com.gamers.Entities.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.ws.rs.*;

@Stateless
@Path("/dictionary")
@Local(DictionaryInterface.class)
public class DictionaryBean implements DictionaryInterface
{

    @Resource
    private SessionContext sessionContext;

    private JSONObject response;
    private PersonDAO personDAO = new PersonDAO();
    private Person curPerson;
    private DictionaryDAO dictionaryDAO = new DictionaryDAO();

    @POST
    @RolesAllowed({"admin", "user"})
    @Produces("application/json")
    @Path("/new")
    @Override
    public JSONObject create(@FormParam("topic") String topic,
                             @FormParam("text") String text)
    {
        response = new JSONObject();
        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());

        if (text == null)
            return initResponse(false, "Are you braindead or smth? Try to write something in text field");

        Dictionary dictionary = new Dictionary();
        dictionary.setPerson(curPerson);
        dictionary.setText(text);

        if (topic != null)
        {
            dictionary.setTopic(topic);
        }
        dictionaryDAO.create(dictionary);
        return initResponse(true, "Dictionary record created");
    }

    @GET
    @RolesAllowed({"admin", "user"})
    @Produces("application/json")
    @Path("/get/all")
    @Override
    public JSONObject getAll()
    {
        response = new JSONObject();
        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());

        JSONArray jsonArray = new JSONArray();
        for (Dictionary dictionary : dictionaryDAO.findByPersonId(curPerson.getId()))
        {
            JSONObject obj = new JSONObject();
            obj.put("id", dictionary.getId());
            obj.put("topic", dictionary.getTopic());
            obj.put("text", dictionary.getText());
            obj.put("date", dictionary.getDate().toString());
            jsonArray.add(obj);
        }
        response.put("dictionary", jsonArray);
        return initResponse(true, "Dictionary found");
    }


    @GET
    @RolesAllowed({"admin", "user"})
    @Produces("application/json")
    @Path("/get/topic/{topic}")
    @Override
    public JSONObject getAllByTopic(@PathParam("topic") String topic)
    {
        response = new JSONObject();
        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());

        JSONArray jsonArray = new JSONArray();
        for (Dictionary dictionary : dictionaryDAO.findByTheme(topic))
        {
            JSONObject obj = new JSONObject();
            obj.put("id", dictionary.getId());
            obj.put("topic", dictionary.getTopic());
            obj.put("text", dictionary.getText());
            obj.put("date", dictionary.getDate().toString());
            jsonArray.add(obj);
        }
        response.put("dictionary", jsonArray);
        return initResponse(true, "Dictionary found");
    }

    @POST
    @RolesAllowed({"admin", "user"})
    @Produces("application/json")
    @Path("/delete/{id}")
    @Override
    public JSONObject delete(@PathParam("id") long id)
    {
        response = new JSONObject();
        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());

        Dictionary dictionary = dictionaryDAO.findById(id);
        if (dictionary == null)
            return initResponse(true, "Wrong dictionary id");

        if (!dictionary.getPerson().getNickname().equals(sessionContext.getCallerPrincipal().getName()))
            return initResponse(true, "Yeah dude it's not yours");

        dictionaryDAO.delete(dictionary);

        return initResponse(true, "YeAH deleted verY C00l");
    }


    private JSONObject initResponse(Boolean success, String desc)
    {
        response.put("success", success.toString());
        response.put("description", desc);
        return response;
    }
}
