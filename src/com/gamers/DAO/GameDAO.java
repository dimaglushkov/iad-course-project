package com.gamers.DAO;

import com.gamers.Entities.Game;
import com.gamers.Entities.Person;
import com.gamers.Entities.PersonsGame;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GameDAO extends DAOService<Game, Long>
{

    public GameDAO()
    {
        super(Game.class);
    }

    public List<Game> findGamesByNickname(String nickname)
    {

        List<Game> games;
        PersonDAO personDAO = new PersonDAO();
        Person person;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        person = personDAO.findByNickname(nickname);
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ИГРА " +
                "INNER JOIN ЛИЧН_ИГРА USING (ИД_ИГРА) " +
                "WHERE ИД_ЛИЧНОСТЬ = " + person.getId() + ";", Game.class);

        games = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return games;

    }


}
