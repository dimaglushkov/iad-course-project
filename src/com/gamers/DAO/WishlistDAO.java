package com.gamers.DAO;

import com.gamers.Entities.Game;
import com.gamers.Entities.Person;
import com.gamers.Entities.Wishlist;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class WishlistDAO extends DAOService<Wishlist, Long>
{
    public WishlistDAO()
    {
        super(Wishlist.class);
    }

    public List<Game> findWishlistGamesIdByNickname(String nickname)
    {

        List<Game> wishList;
        Person person;
        PersonDAO personDAO = new PersonDAO();

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        person = personDAO.findByNickname(nickname);
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ИГРА " +
                        "INNER JOIN ЖЕЛАЕМОЕ USING (ИД_ИГРА) " +
                        "WHERE ИД_ЛИЧНОСТЬ = " + person.getId() + ";", Game.class);

        wishList = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return wishList;

    }
}
