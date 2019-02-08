package com.gamers.DAO;

import com.gamers.Entities.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
@Stateless
public class PersonDAO {

    @PersistenceContext
    private EntityManager em;

    public Person findByNickname(String name) {
        return  em.find(Person.class, name);
    }

}
*/

public class PersonDAO extends GenericDAOImpl<Person, Long>{

    public PersonDAO(){
        type = Person.class;

    }

    public Person findByNickname(String nickname){
        EntityManager entityManager = getEntityManager();
        return entityManager.createQuery("select e from Person e where e.nickname = :nickname", Person.class).setParameter("nickname", nickname).getSingleResult();
    }
}