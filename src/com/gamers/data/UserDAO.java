package com.gamers.data;

import java.util.*;

import com.gamers.entities.User;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Пользователь</strong>
 * @see  User
 */
public class UserDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public UserDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Пользователь</strong>
     * @param user обьект класса User (в БД сущность <strong>Г_Пользователь</strong>)
     * @see User
     */
    public void save(User user)
    {
        dbOperations.createOrUpdateEntity(user);
    }
    /**
     * Метод позволяет удалить запись в сущности <strong>Г_Пользователь</strong>
     * @param user обьект класса User (в БД сущность <strong>Г_Пользователь</strong>)
     * @see User
     */
    public void delete(User  user)
    {
        dbOperations.deleteEntity(user);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Пользователь</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Пользователь</strong>, найденная по ИД
     * @see User
     */
    public User getById(long id)
    {
        return (User)dbOperations.findEntityById(User.class, id);
    }

    /**
     * Метод возвращает запись из сущности <strong>Г_Пользователь</strong> с определенными логином (mail) и паролеи
     * @param email  логин - email нужного пользователя
     * @param password пароль нужного пользователя
     * @return Запись из сущности <strong>Г_Пользователь</strong>, найденная по email и password
     */
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
}