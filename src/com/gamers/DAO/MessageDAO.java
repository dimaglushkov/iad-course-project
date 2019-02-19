package com.gamers.DAO;

import com.gamers.Entities.Message;
import com.gamers.Entities.Person;

import java.util.List;

public class MessageDAO extends DAOService<Message, Long>
{
    public MessageDAO()
    {
        super(Message.class);
    }

    /*public void createWithFewRecipients(Message message, List<String> recipients) throws CloneNotSupportedException
    {

        PersonDAO personDAO = new PersonDAO();

        for (String recipient : recipients)
        {
            Message newMessage = message.clone();
            newMessage.setTo(personDAO.findByNickname(recipient));
            create(newMessage);
        }
    }
*/


}
