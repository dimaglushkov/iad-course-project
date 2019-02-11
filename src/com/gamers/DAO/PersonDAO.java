package com.gamers.DAO;

import com.gamers.Entities.Person;

import java.util.List;

public class PersonDAO {

    private DBOperations dbOperations;

    public PersonDAO()
    {
        dbOperations = new DBOperations();
    }

    public void save(Person person)
    {
        dbOperations.createEntity(person);
    }

    public void update(Person person)
    {
        dbOperations.updateEntity(person);
    }

    public void delete(Person  person)
    {
        dbOperations.deleteEntity(person);
    }

    public Person getById(long id)
    {
        return (Person)dbOperations.findEntityById(Person.class, id);
    }


    public Person getByEmailAndPassword(String email, String password)
    {
        List<Person> Persons = dbOperations.queryWithTwoConditions(Person.class, String.class, String.class,
                email, password, "email", "password");

        if(Persons.size() > 1)
            throw new IllegalStateException("More than one Person with the combination of email and password!");
        else if( Persons.size() == 0)
            return null;

        return Persons.get(0);
    }

    public boolean hasPersonWithSuchEmail(String email)
    {
        List<Person> Persons = dbOperations.queryWithCondition(Person.class, String.class, email, "email");

        return Persons.size() > 0;
    }

    public Person getByEmail(String email)
    {
        List<Person> Persons = DBOperations.queryWithCondition(Person.class, String.class, email, "email");

        if(Persons.size() > 1)
            throw new IllegalStateException("More than one Person with the combination of email and password!");
        else if( Persons.size() == 0)
            return null;

        return Persons.get(0);
    }

    public List<Person> getAll()
    {
        return dbOperations.selectAll(Person.class);
    }
}
