package com.gamers.DAO;

import com.gamers.Entities.Friendship;
import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
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
                "WHERE ДРУЖБА.ИД_ЛИЧНОСТЬ = " + person.getId() + ";", Person.class);

        friends = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return friends;

    }

}
