package com.boatguys.buisiness;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("easter-egg")
@Stateless
public class Converter
{
    @Inject
    private NotificationSender sender;

    private int parrotsInSnake = 38;

    public int fromParraotsToSnake(int parrots)
    {
        return parrots/parrotsInSnake;
    }

    public int fromSnakesToParrots(int snakes)
    {
        return  snakes*parrotsInSnake;
    }

    @POST
    @Path("/send/{message}")
    public void checkJms(@PathParam("message") String message) throws JMSException, NamingException
    {
        sender.sendMessage(message);
        System.out.println("Message for checking jms "+ message);
    }
}
