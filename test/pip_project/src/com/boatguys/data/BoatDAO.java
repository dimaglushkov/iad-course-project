package com.boatguys.data;

import com.boatguys.entities.Boat;

import java.util.List;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.1
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ЛОДКА</strong>
 * @see Boat
 */
public class BoatDAO
{
    private DBOperations dbOperations;
    
    public BoatDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>ЛОДКА</strong>
     * @param boat обьект класса Boat (в БД сущность <strong>ЛОДКА</strong>)
     * @see Boat
     */
    public void save(Boat boat)
    { 
        dbOperations.createEntity(boat);
    }

    public void update(Boat boat)
    {
        dbOperations.updateEntity(boat);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>ЛОДКА</strong>
     * @param boat обьект класса Boat (в БД сущность <strong>ЛОДКА</strong>)
     * @see Boat
     */
    public void delete(Boat boat)
    {
        dbOperations.deleteEntity(boat);
    }
    /**
     * Метод возвращет запись из сущности <strong>ЛОДКА</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>ЛОДКА</strong>, найденная по ИД
     */
    public Boat getById(long id)
    {
        return (Boat)dbOperations.findEntityById(Boat.class, id);
    }
    /**
     * Метод возвращет все записи из сущности <strong>ЛОДКА</strong> (select * ...)
     * @return Возвращает List всех запичей из сущности <strong>ЛОДКА</strong>
     */
    public List<Boat> getAll()
    {
        return dbOperations.selectAll(Boat.class);
    }
}