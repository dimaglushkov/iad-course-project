package com.gamers.data;

import com.gamers.entities.Task;
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
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Задача</strong>
 * @see Task
 */
public class TaskDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public TaskDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Задача</strong>
     * @param task обьект класса Task (в БД сущность <strong>Г_Задача</strong>)
     * @see Task
     */
    public void save(Task task)
    {
        dbOperations.createOrUpdateEntity(task);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Задача</strong>
     * @param task обьект класса Task (в БД сущность <strong>Г_Задача</strong>)
     * @see Task
     */
    public void delete(Task task) { dbOperations.deleteEntity(task); }
    /**
     * Метод возвращет запись из сущности <strong>Г_Задача</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Задача</strong>, найденная по ИД
     * @see Task
     */
    public Task getByID(long id) throws EntityNotFoundException
    {
        return (Task)dbOperations.findEntityById(Task.class, id);
    }

}