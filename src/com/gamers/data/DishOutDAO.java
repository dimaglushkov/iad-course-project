package com.gamers.data;

/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Вынос_мусора</strong>
 * @see DishOut
 */
public class DishOutDAO
{
    private  DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public DishOutDAO()
    {            
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Вынос_мусора</strong>
     * @param out обьект класса DishOut (в БД сущность <strong>Г_Вынос_мусора</strong>)
     * @see DishOut
     */
    public void save(DishOut out)
    {
        dbOperations.createOrUpdateEntity(out);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Вынос_мусора</strong>
     * @param out обьект класса DishOut (в БД сущность <strong>Г_Вынос_мусора</strong>)
     * @see DishOut
     */
    public void delete(DishOut  out)
    {
        dbOperations.deleteEntity(out);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Вынос_мусора</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Вынос_мусора</strong>, найденная по ИД
     * @see DishOut
     */
    public DishOut getByID(long id)
    {
        return (DishOut)dbOperations.findEntityById(DishOut.class, id);
    }


}