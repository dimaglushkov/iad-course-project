package com.gamers.Beans;


import com.gamers.DAO.MessageDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Person;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.sql.Timestamp;
import java.util.List;

@MessageDriven(
        name = "ReportNotifierTopic",
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "javax.jms.Topic"
                ),
                @ActivationConfigProperty(
                        propertyName = "destinationName",
                        propertyValue = "jms/ReportNotifierTopic"
                )
        },
        mappedName = "jms/ReportNotifierTopic")
public class ReportNotificationReceiver implements MessageListener
{

    private MessageDAO messageDAO = new MessageDAO();
    private PersonDAO personDAO = new PersonDAO();

    @Override
    public void onMessage(Message msg)
    {
        com.gamers.Entities.Message DBMessage;
        try
        {
            TextMessage message = (TextMessage) msg;
            DBMessage = new com.gamers.Entities.Message();
            DBMessage.setFrom(personDAO.findByNickname(message.getStringProperty("from")));
            DBMessage.setMessageTopic("Im a cry baby...");
            DBMessage.setMessageText("New report received!\nTopic: "+ message.getText());



            List<Person> personList = personDAO.findByGroupName("admin");

            for(int i = 0; i < personList.size(); i++)
            {
                com.gamers.Entities.Message messageToSend = DBMessage.clone();
                messageToSend.setTo(personList.get(i));
                messageDAO.create(messageToSend);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
