package com.gamers.data;

import com.gamers.entities.Event;

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
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Событие</strong>
 * @see Event
 */
public class EventDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public EventDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Событие</strong>
     * @param event обьект класса Event (в БД сущность <strong>Г_Событие</strong>)
     * @see Event
     */
    public void save(Event event)
    {
        dbOperations.createOrUpdateEntity(event);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Событие</strong>
     * @param event обьект класса Event (в БД сущность <strong>Г_Событие</strong>)
     * @see Event
     */
    public void delete(Event event)
    {
        dbOperations.deleteEntity(event);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Событие</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>Г_Событие</strong>, найденная по ИД
     * @see Event
     */
    public Event getByID(long id)
    {
        return (Event) dbOperations.findEntityById(Event.class, id);
    }
    /**
     * Метод возвращает запись из сущности <strong>Г_Событие</strong> с оределенным типом
     * @param type Тип
     * @return Запись/Записи из сущности <strong>Г_Событие</strong>, найденная по ТИП
     * @see Event
     */
    public List<Event> getAllForType(String type)
    {
        return dbOperations.queryWithCondition(Event.class, String.class, type, "type");
    }
}