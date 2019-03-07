package com.gamers.DAO;

import com.gamers.Beans.FriendsInterface;
import com.gamers.Entities.Friendship;
import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class FriendshipDAO extends DAOService<Friendship, Long>
{
    public FriendshipDAO()
    {
        super(Friendship.class);
    }

    public List<Person> findFriendsByNickname(String nickname)
    {
        PersonDAO personDAO = new PersonDAO();
        Person person = personDAO.findByNickname(nickname);

        List<Friendship> friendShipList = this.findAll();

        if (friendShipList == null)
            return null;

        List<Person> friends = new LinkedList<>();

        for (Friendship curFriendShip : friendShipList)
        {
            if (curFriendShip.getPerson().getId() == person.getId() && curFriendShip.isConfirmed())
                friends.add(curFriendShip.getFriend());

            if (curFriendShip.getFriend().getId() == person.getId() && curFriendShip.isConfirmed())
                friends.add(curFriendShip.getPerson());
        }

        return friends;

    }

    public List<Person> findRequestsByNickname(String nickname)
    {
        PersonDAO personDAO = new PersonDAO();
        Person person = personDAO.findByNickname(nickname);

        List<Friendship> friendShipList = this.findAll();

        if (friendShipList == null)
            return null;

        List<Person> friends = new LinkedList<>();

        for (Friendship curFriendShip : friendShipList)
        {
            if (curFriendShip.getFriend().getId() == person.getId() && !curFriendShip.isConfirmed())
                friends.add(curFriendShip.getPerson());
        }

        return friends;
    }

    public Friendship findFriendshipByNicknames(String nickname1, String nickname2)
    {
        PersonDAO personDAO = new PersonDAO();

        Person person1 = personDAO.findByNickname(nickname1);
        Person person2 = personDAO.findByNickname(nickname2);

        if (person1 == null || person2 == null)
            return null;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Friendship friendship;

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ДРУЖБА WHERE ИД_ЛИЧНОСТЬ = " + person1.getId() + " AND ИД_ДРУГ = " + person2.getId() + ";"
                , Friendship.class);

        try
        {
            friendship = (Friendship) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            Query query2 = entityManager.createNativeQuery(
                    "SELECT * FROM  ДРУЖБА WHERE ИД_ЛИЧНОСТЬ = " + person2.getId() + " AND ИД_ДРУГ = " + person1.getId() + ";"
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

    public void acceptRequest(Friendship friendship)
    {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("UPDATE ДРУЖБА SET ПОДТВЕРЖДЕНО = true WHERE ИД_ДРУЖБА = " + friendship.getId() + ";");
        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void declineRequest(Friendship friendship)
    {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("DELETE FROM ДРУЖБА WHERE ИД_ДРУЖБА = " + friendship.getId() + ";");
        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
