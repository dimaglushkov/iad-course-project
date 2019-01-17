package com.gamers.data;

/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Развлечение</strong>
 * @see Entertainment
 */
public class EntertainmentDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public EntertainmentDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Развлечение</strong>
     * @param enter обьект класса Entertainment (в БД сущность <strong>Г_Развлечение</strong>)
     * @see Entertainment
     */
    public void save(Entertainment enter)
    {
        dbOperations.createOrUpdateEntity(enter);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Развлечение</strong>
     * @param enter обьект класса Entertainment (в БД сущность <strong>Г_Развлечение</strong>)
     * @see Entertainment
     */
    public void delete(Entertainment  enter)
    {
        dbOperations.deleteEntity(enter);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Развлечение</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Развлечение</strong>, найденная по ИД
     * @see Entertainment
     */
    public Entertainment getByID(long id)
    {
        return (Entertainment)dbOperations.findEntityById(Entertainment.class, id);
    }
}