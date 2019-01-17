package com.gamers.data;

import com.gamers.entities.Message;
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
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Сообщения</strong>
 * @see Message
 */
public class MessageDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public MessageDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или изменить запись в сущности <strong>Г_Сообщения</strong>
     * @param message обьект класса Message (в БД сущность <strong>Г_Сообщения</strong>)
     * @see Message
     */
    public void save(Message message)
    {
        dbOperations.createOrUpdateEntity(message);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Сообщения</strong>
     * @param message обьект класса Message (в БД сущность <strong>Г_Сообщения</strong>)
     * @see Message
     */
    public void delete(Message  message)
    {
        dbOperations.deleteEntity(message);
    }
    /**
 * Метод возвращет запись из сущности <strong>Г_Сообщения</strong> (select по ИД)
 * @param id Уникальный идентификатор для каждой записи
 * @return Запись из сущности <strong>Г_Сообщения</strong>, найденная по ИД
 * @see Message
 */
    public Message getById(long id)
    {
        return (Message)dbOperations.findEntityById(Message.class, id);
    }

    /**
     * Метод вызывает postgressql функцию  PRIVATE_MESSAGES (ID_ОТПРАВИТЕЛЬ integer, ID_ПОЛУЧАТЕЛЯ INTEGER)
     * @param firstUser Уникальный идентификатор 1 пользователя личной преписки
     * @param secondUser Уникальный идентификатор 2 пользователя личной преписки
     * @return Возвращает все сообщения между пользователями с ИД (1 и 2)
     */
    public List<Message> getDialogBetweenTwoUsers(User firstUser, User secondUser)
    {
        List<Message> dialog = dbOperations.nativeQuery("select * from Г_Сообщения("+firstUser.getId()+","+secondUser.getId()+")", Message.class);

        return dialog;
    }

    public List<Message> getLastMessagesForUser(User user)
    {

        List<Message> lastMessages =dbOperations.nativeQuery("select * from Г_Сообщения("+user.getId()+")",Message.class);
        
        return lastMessages;
    }
}