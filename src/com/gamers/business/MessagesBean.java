package com.gamers.business;

import com.gamers.data.MessageDAO;
import com.gamers.data.UserDAO;
import com.gamers.entities.Message;
import com.gamers.entities.User;
import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import java.util.Date;
import java.util.List;

@Stateful
@Local(Messages.class)
public class MessagesBean implements Messages
{
    @Resource
    private SessionContext context;
    private User loggedUser;

    private UserDAO userDAO = new UserDAO();
    private MessageDAO messageDAO = new MessageDAO();

    private Gson gson = new Gson();

    public MessagesBean()
    {
        loggedUser = userDAO.getByEmail(context.getCallerPrincipal().getName());
    }

    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getDialogWithUser(String username)
    {
        User anotherUser = userDAO.getByEmail(username);

        List<Message> dialog = messageDAO.getDialogBetweenTwoUsers(loggedUser, anotherUser);

        return gson.toJson(dialog);
    }

    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void send(String message, String usernameOfReciever)
    {
        User anotherUser = userDAO.getByEmail(usernameOfReciever);

        Message newMessage = new Message(loggedUser, anotherUser, message, new Date());

        messageDAO.save(newMessage);
    }

    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getLastUserMessages()
    {
        List<Message> lastMessages = messageDAO.getLastMessagesForUser(loggedUser);

        return gson.toJson(lastMessages);
    }

}
