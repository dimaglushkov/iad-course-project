package com.gamers.DAO;

import com.gamers.Entities.Dictionary;
import com.gamers.Entities.Event;
import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class EventDAO extends DAOService<Event, Long>
{
    public EventDAO()
    {
        super(Event.class);
    }

    public List<Event> findByDateForPerson(Date date, Person person)
    {

        List<Event> events;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        try
        {
            Query query = entityManager.createNativeQuery(
                    "SELECT * " +
                            "FROM СОБЫТИЕ " +
                            "WHERE ДАТА = '" + date.toString() + "' AND ИД_ЛИЧНОСТЬ = "  +  person.getId() + ";", Event.class);

            events = query.getResultList();
        }
        catch (NoResultException e)
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return events;
    }

}
