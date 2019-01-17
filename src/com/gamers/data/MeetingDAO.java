package com.gamers.data;

/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Встреча</strong>
 * @see Meeting
 */
public class MeetingDAO
{
    private DBOperations dbOperations;

    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public MeetingDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или изменить запись в сущности <strong>Г_Встреча</strong>
     * @param meeting обьект класса Meeting (в БД сущность <strong>Г_Встреча</strong>)
     * @see Meeting
     */
    public void save(Meeting meeting)
    {
        dbOperations.createOrUpdateEntity(meeting);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Встреча</strong>
     * @param meeting обьект класса Meeting (в БД сущность <strong>Г_Встреча</strong>)
     * @see Meeting
     */
    public void delete(Meeting meeting)
    {
        dbOperations.deleteEntity(meeting);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Встреча</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>Г_Встреча</strong>, найденная по ИД
     * @see Meeting
     */
    public Meeting getByID(long id)
    {
        return (Meeting) dbOperations.findEntityById(Meeting.class, id);
    }



}