package com.boatguys.data;

import com.boatguys.entities.BaseBoat;

import java.util.List;

/**
 * @author Хрулев Виктор, Возжаев Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong>
 * @see BaseBoat
 */
public class BaseBoatDAO
{
    private DBOperations dbOperations;
    
    public BaseBoatDAO()
    {
        dbOperations = new DBOperations();
    }

    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong>
     * @param baseBoat обьект класса BaseBoat (в БД сущность <strong>БАЗОВЫЕ_ЛОДКИ</strong>)
     */
    public void save(BaseBoat baseBoat)
    {
        dbOperations.createEntity(baseBoat);
    }

    public void update(BaseBoat baseBoat)
    {
        dbOperations.updateEntity(baseBoat);
    }

    /**
     * Мктод позволяет удалить запись в сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong>
     * @param baseBoat обьект класса BaseBoat (в БД сущность <strong>БАЗОВЫЕ_ЛОДКИ</strong>)
     * @see BaseBoat
     */
    public void delete(BaseBoat baseBoat)
    {
        dbOperations.deleteEntity(baseBoat);
    }

    /**
     * Метод возвращет запись из сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong>, найденная по ИД
     * @see BaseBoat
     */
    public BaseBoat getById(long id)
    {
        return (BaseBoat)dbOperations.findEntityById(BaseBoat.class, id);
    }

    /**
     * Метод возвращет все записи из сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong> (select * ...)
     * @return Возвращает List всех запичей из сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong>
     * @see BaseBoat
     */
    public List<BaseBoat> getAll()
    {
        return dbOperations.selectAll(BaseBoat.class);
    }

}