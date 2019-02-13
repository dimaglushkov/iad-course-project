package com.boatguys.buisiness;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.websocket.Session;

public class NotificationsListener implements MessageListener
{
    @Override
    public void onMessage(Message message)
    {
        TextMessage textMessage = (TextMessage) message;
        try
        {
            for(Session session : NotificationEndpoint.getPeers())
                session.getAsyncRemote().sendText(textMessage.getText());

        } catch (JMSException e)
        {

            e.printStackTrace();

        }

    }
}
