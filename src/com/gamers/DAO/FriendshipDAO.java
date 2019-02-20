package com.gamers.DAO;

import com.gamers.Beans.FriendsInterface;
import com.gamers.Entities.Friendship;
import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class FriendshipDAO extends DAOService<Friendship, Long>
{
    public FriendshipDAO()
    {
        super(Friendship.class);
    }

    public List<Person> findFriendsByNickname(String nickname)
    {
        PersonDAO personDAO = new PersonDAO();
        Person person;

        List<Person> friends;
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        person = personDAO.findByNickname(nickname);

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ЛИЧНОСТЬ " +
                "INNER JOIN ДРУЖБА ON ЛИЧНОСТЬ.ИД_ЛИЧНОСТЬ = ДРУЖБА.ИД_ДРУГ " +
                "WHERE ДРУЖБА.ИД_ЛИЧНОСТЬ = " + person.getId() + " AND СТАТУС = true;", Person.class);
        //TODO: check

        try
        {
            friends = query.getResultList();
        }
        catch (NoResultException e)
        {
            friends = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return friends;

    }

    public Friendship findFriendshipByNicknames(String nickname1, String nickname2)
    {
        PersonDAO personDAO = new PersonDAO();

        Person person1 = personDAO.findByNickname(nickname1);
        Person person2 = personDAO.findByNickname(nickname2);

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Friendship friendship;

        Query query = entityManager.createNativeQuery(
                "SELECT * ДРУЖБА WHERE ИД_ЛИЧНОСТЬ = " + person1.getId() + " AND ИД_ДРУГ = " + person2 + ";"
                , Friendship.class);

        try
        {
            friendship = (Friendship) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            Query query2 = entityManager.createNativeQuery(
                    "SELECT * ДРУЖБА WHERE ИД_ЛИЧНОСТЬ = " + person2.getId() + " AND ИД_ДРУГ = " + person1 + ";"
                    , Friendship.class);

            try
            {
                friendship = (Friendship) query.getSingleResult();
            }
            catch (NoResultException ex)
            {
                friendship = null;
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return friendship;

    }


}
