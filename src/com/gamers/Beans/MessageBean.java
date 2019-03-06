package com.gamers.Beans;

import com.gamers.DAO.MessageDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Message;
import com.gamers.Entities.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;

@SuppressWarnings("Duplicates")
@Stateless
@Path("message")
@Local(MessageInterface.class)
public class MessageBean implements MessageInterface
{

    @Resource
    SessionContext sessionContext;

    private JSONObject response;
    private PersonDAO personDAO = new PersonDAO();
    private MessageDAO messageDAO = new MessageDAO();
    private Person curPerson;


    @POST
    @Path("send")
    @Produces("application/json")
    @RolesAllowed({"admin","user"})
    @Override
    public JSONObject send(@FormParam("receiver") String receiver, @FormParam("topic") String topic, @FormParam("text") String text)
    {
        response = new JSONObject();
        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        Message message = new Message();
        if (text == null || receiver == null)
            return initResponse(false, "Empty message or receiver's nickname!");

        Person receiverPerson = personDAO.findByNickname(receiver);
        if (receiverPerson == null)
            return initResponse(false, "Wrong receiver nickname");

        message.setFrom(curPerson);
        message.setTo(receiverPerson);
        message.setMessageText(text);
        message.setMessageTopic(topic);

        messageDAO.create(message);

        return initResponse(true, "Message sent");
    }

    @GET
    @Path("out")
    @Produces("application/json")
    @RolesAllowed({"admin","user"})
    @Override
    public JSONObject getBySender()
    {
        response = new JSONObject();
        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        List<Message> messages = messageDAO.findOut(curPerson.getId());
        JSONArray jsonArray = new JSONArray();
        for(Message message : messages)
        {
            JSONObject obj = new JSONObject();
            obj.put("id", message.getId());
            obj.put("from", curPerson.getNickname());
            obj.put("to", message.getTo().getNickname());
            obj.put("topic", message.getMessageTopic());
            obj.put("text", message.getMessageText());
            obj.put("date", message.getDate());
            jsonArray.add(obj);
        }
        response.put("messages", jsonArray);
        return initResponse(true, "Messages found");
    }

    @GET
    @Path("in")
    @Produces("application/json")
    @RolesAllowed({"admin","user"})
    @Override
    public JSONObject getByReceiver()
    {
        response = new JSONObject();
        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        List<Message> messages = messageDAO.findIn(curPerson.getId());
        JSONArray jsonArray = new JSONArray();
        for(Message message : messages)
        {
            JSONObject obj = new JSONObject();
            obj.put("id", message.getId());
            obj.put("from", message.getFrom().getNickname());
            obj.put("to", curPerson.getNickname());
            obj.put("topic", message.getMessageTopic());
            obj.put("text", message.getMessageText());
            obj.put("date", message.getDate());
            jsonArray.add(obj);
        }
        response.put("messages", jsonArray);
        return initResponse(true, "Messages found");
    }

    private JSONObject initResponse(boolean success, String description)
    {
        response.put("success", success);
        response.put("description", description);
        return response;
    }
}
