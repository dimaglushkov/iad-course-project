package com.boatguys.data;

import com.boatguys.entities.Message;
import com.boatguys.entities.User;

import java.util.List;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong>
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
     * Метод позволяет добавить или измениьть запись в сущности <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong>
     * @param message обьект класса Message (в БД сущность <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong>)
     * @see Message
     */
    public void save(Message message)
    {
        dbOperations.createEntity(message);
    }

    public void update(Message message)
    {
        dbOperations.updateEntity(message);
    }

    /**
     * Мктод позволяет удалить запись в сущности <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong>
     * @param message обьект класса Message (в БД сущность <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong>)
     * @see Message
     */
    public void delete(Message  message)
    {
        dbOperations.deleteEntity(message);
    }
    /**
 * Метод возвращет запись из сущности <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong> (select по ИД)
 * @param id Уникальный идентификатор для каждой записи
 * @return Запись из сущности <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong>, найденная по ИД
 * @see Message
 */
    public Message getById(long id)
    {
        return (Message)dbOperations.findEntityById(Message.class, id);
    }

    /**
     * Метод вызывает postgressql функцию  PRIVATE_MESSAGES (ID_ОТПРАВИТЕЛЬ integer, ID_ПОЛУЧАТЕЛЯ INTEGER)
     * @param firstUser Уникльный идентификатор 1 пользователя личной преписки
     * @param secondUser Уникльный идентификатор 2 пользователя личной преписки
     * @return ВОзвращает все сообщения между рользователями с ИД (1 и 2)
     */
    public List<Message> getDialogBetweenTwoUsers(User firstUser, User secondUser)
    {
        List<Message> dialog = dbOperations.nativeQuery("select * from PRIVATE_MESSAGES("+firstUser.getId()+","+secondUser.getId()+")", Message.class);

        return dialog;
    }

    public List<Message> getLastMessagesForUser(User user)
    {

        List<Message> lastMessages =dbOperations.nativeQuery("select * from LAST_MESSAGES("+user.getId()+")",Message.class);
        
        return lastMessages;
    }
}