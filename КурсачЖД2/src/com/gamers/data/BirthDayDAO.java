package com.gamers.data;

import com.gamers.entities.BirthDay;

import javax.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import javax.persistence.criteria.*;

/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_День_рождения</strong>
 * @see BirthDay
 */
public class BirthDayDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public BirthDayDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_День_рождения</strong>
     * @param bday обьект класса BirthDay (в БД сущность <strong>Г_День_рождения</strong>)
     * @see BirthDay
     */
    public void save(BirthDay bday)
    {
        dbOperations.createOrUpdateEntity(bday);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_День_рождения</strong>
     * @param bday обьект класса BirthDay (в БД сущность <strong>Г_День_рождения</strong>)
     * @see BirthDay
     */
    public void delete(BirthDay bday)
    { 
        dbOperations.deleteEntity(bday);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_День_рождения</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>Г_День_рождения</strong>, найденная по ИД
     * @see BirthDay
     */
    public BirthDay getByID(long id)
    {
        return (BirthDay)dbOperations.findEntityById(BirthDay.class, id);
    }


}