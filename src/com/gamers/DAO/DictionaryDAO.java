package com.gamers.DAO;

import com.gamers.Entities.Dictionary;
import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class DictionaryDAO extends DAOService<Dictionary, Long> {

    public DictionaryDAO(){
        super(Dictionary.class);
    }


    public List<Dictionary> findByPersonId(Person person)
    {
        List<Dictionary> dictionaries;
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        try
        {
            Query query = entityManager.createNativeQuery(
                    "SELECT ИД_ДНЕВНИК, ИД_ЛИЧНОСТЬ, ТЕМА, ЗАПИСЬ, ДАТА " +
                            "FROM ДНЕВНИК " +
                            "WHERE ИД_ЛИЧНОСТЬ = '" + person.getId() + "';", Dictionary.class);

            dictionaries = query.getResultList();
        }
        catch (NoResultException e)
        {
            dictionaries = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return dictionaries;
    }




}
