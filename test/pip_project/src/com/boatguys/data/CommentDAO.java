package com.boatguys.data;

import com.boatguys.entities.Comment;
import com.boatguys.entities.ForumRecord;

import java.util.List;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>КОМЕНТАРИИ</strong>
 * @see Comment
 */
public class CommentDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public CommentDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>КОМЕНТАРИИ</strong>
     * @param comment обьект класса Comment (в БД сущность <strong>КОМЕНТАРИИ</strong>)
     * @see Comment
     */
    public void save(Comment comment)
    {
        dbOperations.createEntity(comment);
    }

    public void update(Comment comment)
    {
        dbOperations.updateEntity(comment);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>КОМЕНТАРИИ</strong>
     * @param comment обьект класса BoatDimensions (в БД сущность <strong>КОМЕНТАРИИ</strong>)
     * @see Comment
     */
    public void delete(Comment comment)
    { 
        dbOperations.deleteEntity(comment);
    }
    /**
     * Метод возвращет запись из сущности <strong>КОМЕНТАРИИ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>КОМЕНТАРИИ</strong>, найденная по ИД
     * @see Comment
     */
    public Comment getByID(long id) 
    {
        return (Comment)dbOperations.findEntityById(Comment.class, id);
    }

    /**
     * Метод возвращет коментарии к записи на форуме (сущнности ЗАПИСЬ_НА_ФОРУМЕ)
     * @param forumRecord обьект класса ForumRecord (в БД сущность <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>)
     * @see ForumRecord
     * @return Возвращает List всех запичей из сущности <strong>КОМЕНТАРИИ</strong> к определееной записи из сущьности ЗАПИСЬ_НА_ФОРУМЕ
     * @see Comment
     */

    public List<Comment> getAllForForumRecord(ForumRecord forumRecord)
    {
        return dbOperations.queryWithCondition(Comment.class, ForumRecord.class, forumRecord, "forumRecord");
    }
}