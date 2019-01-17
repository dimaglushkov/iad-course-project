package com.gamers.data;

import com.gamers.entities.Note;

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
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Заметка</strong>
 * @see Note
 */
public class NoteDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public NoteDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Заметка</strong>
     * @param note обьект класса Note (в БД сущность <strong>Г_Заметка</strong>)
     * @see Note
     */
    public void save(Note note)
    {
        dbOperations.createOrUpdateEntity(note);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Заметка</strong>
     * @param note обьект класса Note (в БД сущность <strong>Г_Заметка</strong>)
     * @see Note
     */
    public void delete(Note note)
    {
        dbOperations.deleteEntity(note);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Заметка</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Заметка</strong>, найденная по ИД
     * @see Note
     */
    public Note getByID(long id) throws EntityNotFoundException
    {
        return (Note)dbOperations.findEntityById(Note.class, id);
    }

}