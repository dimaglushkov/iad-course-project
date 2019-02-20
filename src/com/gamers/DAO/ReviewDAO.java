package com.gamers.DAO;

import com.gamers.Entities.Game;
import com.gamers.Entities.Person;
import com.gamers.Entities.Review;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ReviewDAO extends DAOService<Review, Long>
{

    public ReviewDAO()
    {
        super(Review.class);
    }

    public List<Review> findReviewsByNickname(String nickname)
    {
        Person person;
        PersonDAO personDAO = new PersonDAO();
        List<Review> reviews;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        person = personDAO.findByNickname(nickname);
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ОБЗОР WHERE ИД_ЛИЧНОСТЬ = " + person.getId() + ";", Review.class);

        reviews = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return reviews;
    }


}
