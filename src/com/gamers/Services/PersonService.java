package com.gamers.Services;

import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

public class PersonService extends DAOService<Person, Long>{

    public PersonService(){
        super(Person.class);
    }

    public Person findByNickname(String nickname) throws EntityNotFoundException {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("SELECT * FROM ЛИЧНОСТЬ WHERE НИКНЕЙМ = '" + nickname + "';", Person.class);

        Person person = (Person) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (person == null)
            throw new EntityNotFoundException("No user with such nickname");

        return person;
    }

    public Person findByEmail(String email) throws  EntityNotFoundException{

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("SELECT * FROM ЛИЧНОСТЬ WHERE ЭЛ_ПОЧТА = '" + email + "';", Person.class);

        Person person = (Person) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (person == null)
            throw new EntityNotFoundException("No user with such email");

        return person;
    }

    public Person findByEmailAndPassword(String email, String password) throws EntityNotFoundException{

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("SELECT * FROM ЛИЧНОСТЬ WHERE ЭЛ_ПОЧТА = '" + email + "' AND '" + password + "';", Person.class);

        Person person = (Person) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (person == null)
            throw new EntityNotFoundException("Bad login or password.");

        return person;
    }


}
