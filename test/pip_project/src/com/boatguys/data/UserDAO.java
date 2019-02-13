package com.boatguys.data;

import com.boatguys.entities.User;

import java.util.List;

public class UserDAO
{
    private DBOperations dbOperations;

    public UserDAO()
    {
        dbOperations = new DBOperations();
    }

    public void save(User user)
    {
        dbOperations.createEntity(user);
    }

    public void update(User user)
    {
        dbOperations.updateEntity(user);
    }

    public void delete(User  user)
    {
        dbOperations.deleteEntity(user);
    }

    public User getById(long id)
    {
        return (User)dbOperations.findEntityById(User.class, id);
    }

    public User getByEmailAndPassword(String email, String password)
    {
        List<User> users = dbOperations.queryWithTwoConditions(User.class, String.class, String.class, 
        email, password, "email", "password");

        if(users.size() > 1)
            throw new IllegalStateException("More than one user with the combination of email and password!");
        else if( users.size() == 0)
            return null;

        return users.get(0);
    }

    public boolean hasUserWithSuchEmail(String email)
    {
        List<User> users = dbOperations.queryWithCondition(User.class, String.class, email, "email");

        return users.size() > 0;
    }

    public User getByEmail(String email)
    {
        List<User> users = dbOperations.queryWithCondition(User.class, String.class, email, "email");

        if(users.size() > 1)
            throw new IllegalStateException("More than one user with the combination of email and password!");
        else if( users.size() == 0)
            return null;

        return users.get(0);
    }

    public List<User> getAll()
    {
        return dbOperations.selectAll(User.class);
    }
}