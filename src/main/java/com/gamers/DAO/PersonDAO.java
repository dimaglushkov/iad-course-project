package com.gamers.DAO;

import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonDAO extends GenericDAOImpl<Person, Long>{

    public PersonDAO(){
        type = Person.class;

    }

    public Person findByNickname(String nickname){
        EntityManager entityManager = getEntityManager();
        return entityManager.createQuery("select e from Person e where e.nickname = :nickname", Person.class).setParameter("nickname", nickname).getSingleResult();
    }
}
