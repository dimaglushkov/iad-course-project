package com.gamers.data;

/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Место</strong>
 * @see Place
 */
public class PlaceDAO
{
    private DBOperations dbOperations;

    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public PlaceDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или изменить запись в сущности <strong>Г_Место</strong>
     * @param place обьект класса Competition (в БД сущность <strong>Г_Место</strong>)
     * @see Place
     */
    public void save(Place place)
    {
        dbOperations.createOrUpdateEntity(place);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Место</strong>
     * @param place обьект класса BoatDimensions (в БД сущность <strong>Г_Место</strong>)
     * @see Place
     */
    public void delete(Place place)
    {
        dbOperations.deleteEntity(place);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Место</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>Г_Место</strong>, найденная по ИД
     * @see Place
     */
    public Place getByID(long id)
    {
        return (Place) dbOperations.findEntityById(Place.class, id);
    }

    
}