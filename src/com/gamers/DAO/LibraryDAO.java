package com.gamers.DAO;

import com.gamers.Entities.Game;
import com.gamers.Entities.Library;
import com.gamers.Entities.Person;
import sun.awt.X11.InfoWindow;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

public class LibraryDAO extends DAOService<Library, Long>
{
    public LibraryDAO()
    {
        super(Library.class);
    }

    public Library findByGameAndPerson(Game game, Person person)
    {
        Library library = new Library();
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ЛИЧН_ИГРА " +
                        " WHERE ИД_ЛИЧНОСТЬ = " + person.getId() +
                        " AND ИД_ИГРА = " + game.getId()  +";", Library.class);

        try
        {
            library = (Library) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            library = null;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return library;
    }

    public void deleteGameFromLibraries(Game game)
    {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                "DELETE * FROM ЛИЧН_ИГРА " +
                        " AND ИД_ИГРА = " + game.getId()  +";", Library.class);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
