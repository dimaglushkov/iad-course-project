package com.gamers.DAO;

import com.gamers.Entities.Dictionary;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DictionaryDAO extends DAOService<Dictionary, Long> {

    public DictionaryDAO(){
        super(Dictionary.class);
    }

    public List<Dictionary> findByTheme(String theme)
    {
        List<Dictionary> dictionaries;
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        try
        {
            Query query = entityManager.createNativeQuery(
                    "SELECT ИД_ДНЕВНИК, ИД_ЛИЧНОСТЬ, ТЕМА, ЗАПИСЬ, ДАТА " +
                            "FROM ДНЕВНИК " +
                            "WHERE ТЕМА = '" + theme + "';", Dictionary.class);

            dictionaries = query.getResultList();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return dictionaries;
    }


}
