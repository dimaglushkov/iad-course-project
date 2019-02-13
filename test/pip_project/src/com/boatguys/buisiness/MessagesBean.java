package com.boatguys.buisiness;

import com.boatguys.data.MessageDAO;
import com.boatguys.data.UserDAO;
import com.boatguys.entities.Message;
import com.boatguys.entities.User;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ws.rs.*;
import java.util.Date;
import java.util.List;

@Path("messages")
@Stateful
@Local(Messages.class)
public class MessagesBean implements Messages
{
    @Resource
    private SessionContext context;
    private User loggedUser;

    private UserDAO userDAO = new UserDAO();
    private MessageDAO messageDAO = new MessageDAO();

    private Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy()
    {
        @Override
        public boolean shouldSkipField(FieldAttributes f)
        {
            return (f.getDeclaringClass() == User.class &&
                    (f.getName().equals("password") || f.getName().equals("boats")));
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass)
        {
            return false;
        }
    }).create();

    public MessagesBean()
    {

    }

    @GET
    @Produces("application/json")
    @Path("/{username}")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getDialogWithUser(@PathParam("username") String username)
    {
        loggedUser = userDAO.getByEmail(context.getCallerPrincipal().getName());

        User anotherUser = userDAO.getByEmail(username);

        List<Message> dialog = messageDAO.getDialogBetweenTwoUsers(loggedUser, anotherUser);

        return gson.toJson(dialog);
    }

    @POST
    @Path("/send/{id}")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void send(@FormDataParam("message") String message, @PathParam("id") long id)
    {
        loggedUser = userDAO.getByEmail(context.getCallerPrincipal().getName());

        User anotherUser = userDAO.getById(id);

        Message newMessage = new Message(loggedUser, anotherUser, message, new Date());

        messageDAO.save(newMessage);
    }

    @GET
    @Produces("application/json")
    @Path("/last")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getLastUserMessages()
    {
        loggedUser = userDAO.getByEmail(context.getCallerPrincipal().getName());

        List<Message> lastMessages = messageDAO.getLastMessagesForUser(loggedUser);

        return gson.toJson(lastMessages);
    }

}
