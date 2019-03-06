package com.gamers.DAO;

import com.gamers.Entities.Message;
import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MessageDAO extends DAOService<Message, Long>
{
    public MessageDAO()
    {
        super(Message.class);
    }

    public List<Message> findIn(long personId)
    {
        List<Message> messages;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try
        {
            Query query = entityManager.createNativeQuery(
                    "SELECT * FROM СООБЩЕНИЕ " +
                            " WHERE КОМУ_ЛИЧНОСТЬ = " + personId + " ORDER BY ИД_СООБЩЕНИЕ DESC;", Message.class);

            messages = query.getResultList();
        }
        catch (NoResultException e)
        {
            messages = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return messages;
    }

    public List<Message> findOut(long personId)
    {
        List<Message> messages;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try
        {
            Query query = entityManager.createNativeQuery(
                    "SELECT * FROM СООБЩЕНИЕ " +
                            " WHERE ОТ_ЛИЧНОСТЬ = " + personId + " ORDER BY ИД_СООБЩЕНИЕ DESC;", Message.class);

            messages = query.getResultList();
        }
        catch (NoResultException e)
        {
            messages = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return messages;

    }


}
