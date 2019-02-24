package com.gamers.DAO;

import com.gamers.Entities.Game;
import com.gamers.Entities.Person;
import com.gamers.Entities.Review;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class ReviewDAO extends DAOService<Review, Long>
{

    public ReviewDAO()
    {
        super(Review.class);
    }

    @SuppressWarnings("Duplicates")
    public List<Review> findByNickname(String nickname)
    {
        Person person;
        PersonDAO personDAO = new PersonDAO();
        List<Review> reviews;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        person = personDAO.findByNickname(nickname);
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ОБЗОР WHERE ИД_ЛИЧНОСТЬ = " + person.getId() + ";", Review.class);

        try
        {
            reviews = query.getResultList();
        }
        catch (NoResultException e)
        {
            reviews = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return reviews;
    }

    @SuppressWarnings("Duplicates")
    public List<Review> findByGameId(long id)
    {

        List<Review> reviews;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ОБЗОР WHERE ИД_ИГРА = " + id + ";", Review.class);

        try
        {
            reviews = query.getResultList();
        }
        catch (NoResultException e)
        {
            reviews = null;
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return reviews;
    }


}
