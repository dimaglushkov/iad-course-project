package com.gamers.Beans;

import com.gamers.DAO.EventDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Event;
import com.gamers.Entities.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Path("event")
@Stateless
@Local(EventInterface.class)
public class EventBean implements EventInterface
{

    @Resource
    SessionContext sessionContext;
    private PersonDAO personDAO = new PersonDAO();
    private Person currentPerson;
    private JSONObject response;

    @POST
    @Path("/new")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject create(@FormParam("description") String description,
                             @FormParam("date") String dateStr,
                             @FormParam("time") String timeStr)
    {
        //Date format: 2013-08-22
        //Time format: 14:57:00
        response = new JSONObject();

        if (description == null || dateStr == null || timeStr == null)
            return initResponse(false, "Please feel every field");

        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());

        EventDAO eventDAO = new EventDAO();
        Event event = new Event();

        Date date;
        Time time;
        try
        {
            date = Date.valueOf(dateStr);
            time =Time.valueOf(timeStr);
        }
        catch (IllegalStateException e)
        {
            return initResponse(false, "Please feel every field correctly");
        }

        event.setDate(date);
        event.setDesc(description);
        event.setTime(time);
        event.setPerson(currentPerson);

        eventDAO.create(event);
        return initResponse(true, "Event created");

    }

    @GET
    @Path("/{date}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject findByDate(@PathParam("date") String dateStr)
    {
        response  = new JSONObject();

        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        EventDAO eventDAO = new EventDAO();

        Date date;
        try
        {
            date = Date.valueOf(dateStr);

        }
        catch (IllegalStateException e)
        {
            return initResponse(false, "Wrong date format");
        }
        List<Event> events = eventDAO.findByDateForPerson(date, currentPerson);

        if (events == null)
        {
            return initResponse(false, "No events for this date found");
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
        return initResponse(false, "Events found");
    }


    @POST
    @Path("/delete/{id}")
    @RolesAllowed({"admin", "user"})
    @Produces("application/json")
    @Override
    public JSONObject delete(@PathParam("id") String id)
    {
        response = new JSONObject();
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        EventDAO eventDAO = new EventDAO();

        Event eventToDelete = eventDAO.findById(Long.valueOf(id));

        if (eventToDelete == null)
            return initResponse(false, "Event not found");

        eventDAO.delete(eventToDelete);
        return initResponse(true, "Event deleted");
    }

    private JSONObject initResponse(boolean success, String desc)
    {
        response.put("success", success);
        response.put("description", desc);
        return response;
    }

}
