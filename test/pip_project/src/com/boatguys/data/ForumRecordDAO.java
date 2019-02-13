package com.boatguys.data;

import com.boatguys.entities.ForumRecord;
import com.boatguys.entities.ForumSection;
import com.boatguys.entities.ForumTopic;
import com.boatguys.entities.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>
 * @see ForumRecord
 */
public class ForumRecordDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public ForumRecordDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>
     * @param forumRecord обьект класса ForumRecord (в БД сущность <strong>ForumRecord</strong>)
     * @see ForumRecord
     */
    public void save(ForumRecord forumRecord)
    {
        dbOperations.createEntity(forumRecord);
    }

    public void update(ForumRecord forumRecord)
    {
        dbOperations.updateEntity(forumRecord);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>
     * @param forumRecord обьект класса ForumRecord (в БД сущность <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>)
     * @see ForumRecord
     */
    public void delete(ForumRecord forumRecord)
    {
        dbOperations.deleteEntity(forumRecord);
    }
    /**
     * Метод возвращет запись из сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>ДЕТАЛИ</strong>, найденная по ИД
     * @see ForumRecord
     */
    public ForumRecord getByID(long id) throws EntityNotFoundException
    {
        return (ForumRecord)dbOperations.findEntityById(ForumRecord.class, id);
    }
    /**
     * Метод возвращает запись из  сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong> из определенного раздела
     * @param section Раздел на форуме где хранится запись
     * @return Запись/Записи из сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>, найденная по РАЗДЕЛ
     * @see ForumRecord
     */
    public List<ForumRecord> getAllForSection(ForumSection section)
    {
        String query = "select * from ЗАПИСЬ_НА_ФОРУМЕ a where a.РАЗДЕЛ = '"+section.getSectionName()+"'";

        return DBOperations.nativeQuery(query, ForumRecord.class);
    }
    /**
     * Метод возвращает запись из  сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong> по теме
     * @param topic Тема на форуме где хранится запись
     * @return Запись/Записи из сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>, найденная по ТЕМА
     * @see ForumRecord
     */
    public List<ForumRecord> getAllForTopic(ForumTopic topic)
    {
        return dbOperations.queryWithCondition(ForumRecord.class, ForumTopic.class, topic, "topic");
    }

    /**
     * Метод возвращает запись из сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong> по автору кто ее написал
     * @param author обект класса User - пользователь написавший запись
     * @see User
     * @return  Запись/Записи из сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>, найденная по АВТОР
     * @see ForumRecord
     */
    public List<ForumRecord> getAllFromUser(User author)
    {
        return dbOperations.queryWithCondition(ForumRecord.class, User.class, author, "author");
    }
}