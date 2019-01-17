package com.gamers.data;

/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Вид_развлечения</strong>
 * @see EntertainmentType
 */
public class EntertainmentTypeDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public EntertainmentTypeDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Вид_развлечения</strong>
     * @param producer обьект класса EntertainmentType (в БД сущность <strong>Г_Вид_развлечения</strong>)
     * @see EntertainmentType
     */
    public void save(EntertainmentType producer)
    {
        dbOperations.createOrUpdateEntity(producer);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Вид_развлечения</strong>
     * @param producer обьект класса EntertainmentType (в БД сущность <strong>Г_Вид_развлечения</strong>)
     * @see EntertainmentType
     */
    public void delete(EntertainmentType  producer)
    {
        dbOperations.deleteEntity(producer);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Вид_развлечения</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Вид_развлечения</strong>, найденная по ИД
     * @see EntertainmentType
     */
    public EntertainmentType getByID(long id)
    {
        return (EntertainmentType)dbOperations.findEntityById(EntertainmentType.class, id);
    }
}