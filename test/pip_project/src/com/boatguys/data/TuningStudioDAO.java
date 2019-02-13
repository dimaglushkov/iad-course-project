package com.boatguys.data;

import com.boatguys.entities.TuningStudio;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ТЮНИНГ_АТЕЛЬЕ</strong>
 * @see TuningStudio
 */
public class TuningStudioDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public TuningStudioDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>ТЮНИНГ_АТЕЛЬЕ</strong>
     * @param studio обьект класса TuningStudio (в БД сущность <strong>ТЮНИНГ_АТЕЛЬЕ</strong>)
     * @see TuningStudio
     */
    public void save(TuningStudio studio)
    {
        dbOperations.createEntity(studio);
    }

    public void update(TuningStudio tuningStudio)
    {
        dbOperations.updateEntity(tuningStudio);
    }

    /**
     * Мктод позволяет удалить запись в сущности <strong>ТЮНИНГ_АТЕЛЬЕ</strong>
     * @param studio обьект класса TuningStudio (в БД сущность <strong>ТЮНИНГ_АТЕЛЬЕ</strong>)
     * @see TuningStudio
     */
    public void delete(TuningStudio  studio)
    {
        dbOperations.deleteEntity(studio);
    }
    /**
     * Метод возвращет запись из сущности <strong>ТЮНИНГ_АТЕЛЬЕ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>ТЮНИНГ_АТЕЛЬЕ</strong>, найденная по ИД
     * @see TuningStudio
     */
    public TuningStudio getByID(long id)
    {
        return (TuningStudio)dbOperations.findEntityById(TuningStudio.class, id); 
    }
}