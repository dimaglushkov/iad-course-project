package com.gamers.data;

import com.gamers.entities.MeetingType;


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
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Вид_Встречи</strong>
 * @see MeetingType
 */
public class MeetingTypeDAO
{
    private DBOperations dbOperations;

    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public MeetingTypeDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Вид_Встречи</strong>
     * @param meetingtype обьект класса MeetingType (в БД сущность <strong>Г_Вид_Встречи</strong>)
     * @see MeetingType
     */
    public void save(MeetingType meetingtype)
    {
        dbOperations.createOrUpdateEntity(meetingtype);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Вид_Встречи</strong>
     * @param meetingtype обьект класса MeetingType (в БД сущность <strong>Г_Вид_Встречи</strong>)
     * @see MeetingType
     */
    public void delete(MeetingType meetingtype)
    {
        dbOperations.deleteEntity(meetingtype);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Вид_Встречи</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Вид_Встречи</strong>, найденная по ИД
     * @see MeetingType
     */
    public MeetingType getByID(long id)
    {
        return (MeetingType)dbOperations.findEntityById(MeetingType.class, id);
    }



}