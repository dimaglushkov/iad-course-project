package com.gamers.DAO;

import com.gamers.Entities.Info;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class InfoDAO extends DAOService<Info, Long>
{
    public InfoDAO()
    {
        super(Info.class);
    }

    public Info findByPersonId(long personId)
    {
        Info info;
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ИНФО " +
                        "WHERE ИД_ЛИЧНОСТЬ = '" + personId + "';", Info.class);

        try
        {
            info = (Info) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            info = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return info;
    }

}
