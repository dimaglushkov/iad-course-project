package com.boatguys.data;

import com.boatguys.entities.Detail;

import java.util.List;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ДЕТАЛИ</strong>
 * @see Detail
 */
public class DetailDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public DetailDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>ДЕТАЛИ</strong>
     * @param detail обьект класса Detail (в БД сущность <strong>ДЕТАЛИ</strong>)
     * @see Detail
     */
    public void save(Detail detail)
    {
        dbOperations.createEntity( detail);
    }

    public void update(Detail detail)
    {
        dbOperations.updateEntity(detail);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>ДЕТАЛИ</strong>
     * @param detail обьект класса Detail (в БД сущность <strong>ДЕТАЛИ</strong>)
     * @see Detail
     */
    public void delete(Detail detail)
    {
        dbOperations.deleteEntity(detail);
    }
    /**
     * Метод возвращет запись из сущности <strong>ДЕТАЛИ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>ДЕТАЛИ</strong>, найденная по ИД
     * @see Detail
     */
    public Detail getByID(long id)
    {
        return (Detail)dbOperations.findEntityById(Detail.class, id);
    }
    /**
     * Метод возвращает запись из сущности <strong>ДЕТАЛИ</strong> с оределенным типом
     * @param type Тип детали
     * @return Запись/Записи из сущности <strong>ДЕТАЛИ</strong>, найденная по ТИП
     * @see Detail
     */
    public List<Detail> getAllForType(String type)
    {
        return dbOperations.queryWithCondition(Detail.class, String.class, type, "type");
    }
}