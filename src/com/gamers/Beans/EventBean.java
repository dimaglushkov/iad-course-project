package com.gamers.Beans;

import com.gamers.DAO.EventDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Event;
import com.gamers.Entities.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.runners.Parameterized;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Path("events")
@Stateless
@Local(EventInterface.class)
public class EventBean implements EventInterface
{

    @Resource
    SessionContext sessionContext;
    private PersonDAO personDAO = new PersonDAO();
    private Person currentPerson;

    @POST
    @Path("/new")
    @RolesAllowed({"admin", "user"})
    @Override
    public void create(@FormParam("description") String description, @FormParam("date") String dateStr, @FormParam("time") String timeStr)
    {
        //Date format: 2013-08-22
        //Time format: 14:57:00
        if (description == null || dateStr == null || timeStr == null)
            return;

        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());

        EventDAO eventDAO = new EventDAO();
        Event event = new Event();

        Date date = Date.valueOf(dateStr);
        Time time = Time.valueOf(timeStr);

        event.setDate(date);
        event.setDesc(description);
        event.setTime(time);
        event.setPerson(currentPerson);

        eventDAO.create(event);

    }

    @GET
    @Path("/{date}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject findByDate(@PathParam("date") String dateStr)
    {
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        EventDAO eventDAO = new EventDAO();

        JSONObject response = new JSONObject();
        Date date = Date.valueOf(dateStr);
        List<Event> events = eventDAO.findByDateForPerson(date, currentPerson);

        if (events == null)
        {
            response.put("success", "false");
            response.put("description", "No events for this date found");
            return response;
        }

        JSONArray jsonArray = new JSONArray();

        for(Event event : events)
        {
            JSONObject obj = new JSONObject();
            obj.put("id", event.getId());
            obj.put("date", event.getDate().toString());
            obj.put("time", event.getTime().toString());
            obj.put("description", event.getDesc());
            jsonArray.add(obj);
        }
        response.put("events", jsonArray);
        return response;
    }


    @POST
    @Path("/delete/{id}")
    @RolesAllowed({"admin", "user"})
    @Override
    public void delete(@PathParam("id") String id)
    {
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        EventDAO eventDAO = new EventDAO();

        Event eventToDelete = eventDAO.findById(Long.valueOf(id));

        if (eventToDelete == null)
            return;

        eventDAO.delete(eventToDelete);

    }

}
