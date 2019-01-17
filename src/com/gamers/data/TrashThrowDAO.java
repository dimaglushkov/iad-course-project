package com.gamers.data;
import com.gamers.entities.TrashThrow;

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
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Вынос_мусора</strong>
 * @see TrashThrow
 */
public class TrashThrowDAO
{

    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public TrashThrowDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или изменить запись в сущности <strong>Г_Вынос_мусора</strong>
     * @param Tthrow обьект класса TrashThrow (в БД сущность <strong>Г_Вынос_мусора</strong>)
     * @see TrashThrow
     */
    public void save(TrashThrow Tthrow)
    {
        dbOperations.createOrUpdateEntity(Tthrow);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Вынос_мусора</strong>
     * @param Tthrow обьект класса TrashThrow(в БД сущность <strong>Г_Вынос_мусора</strong>)
     * @see TrashThrow
     */
    public void delete(TrashThrow Tthrow)
    {
        dbOperations.deleteEntity(Tthrow);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Вынос_мусора</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Вынос_мусора</strong>, найденная по ИД
     * @see TrashThrow
     */
    public TrashThrow getByID(long id)
    {
        return (TrashThrow)dbOperations.findEntityById(TrashThrow.class, id);
    }
}