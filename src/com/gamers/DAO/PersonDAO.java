package com.gamers.DAO;

import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PersonDAO extends DAOService<Person, Long>{

    public PersonDAO(){
        super(Person.class);
    }

    public Person findByNickname(String nickname)
    {
        Person person;
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        try
        {
            Query query = entityManager.createNativeQuery("SELECT * FROM ЛИЧНОСТЬ WHERE НИКНЕЙМ = '" + nickname + "';", Person.class);
            person = (Person) query.getSingleResult();
        }
        catch (Exception E)
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return person;
    }

    public Person findByEmail(String email)
    {
        Person person;
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try
        {
            Query query = entityManager.createNativeQuery("SELECT * FROM ЛИЧНОСТЬ WHERE ЭЛ_ПОЧТА = '" + email + "';", Person.class);
            person = (Person) query.getSingleResult();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return null;
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return person;

    }

    public List<Person> findByGroupName(String groupname)
    {

        List<Person> persons;
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        try
        {
            Query query = entityManager.createNativeQuery(
                    "SELECT НИКНЕЙМ, ИД_ЛИЧНОСТЬ, ЭЛ_ПОЧТА, ХЕШ_ПАРОЛЬ " +
                            "FROM ЛИЧНОСТЬ " +
                            "INNER JOIN ГРУППА_ЛИЧН USING (НИКНЕЙМ) " +
                            "WHERE ГРУППА_ЛИЧН.ГРУППА = '" + groupname + "';", Person.class);

            persons = query.getResultList();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return persons;

    }


}
