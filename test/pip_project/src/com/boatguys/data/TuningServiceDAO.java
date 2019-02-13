package com.boatguys.data;

import com.boatguys.entities.TuningService;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ТЮНИНГ_УСЛУГА</strong>
 * @see TuningService
 */
public class TuningServiceDAO
{

    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public TuningServiceDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>ТЮНИНГ_УСЛУГА</strong>
     * @param service обьект класса TuningService (в БД сущность <strong>ТЮНИНГ_УСЛУГА</strong>)
     * @see TuningService
     */
    public void save(TuningService service)
    {
        dbOperations.createEntity(service);
    }

    public void update(TuningService tuningService)
    {
        dbOperations.updateEntity(tuningService);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>ТЮНИНГ_УСЛУГА</strong>
     * @param service обьект класса TuningService (в БД сущность <strong>ТЮНИНГ_УСЛУГА</strong>)
     * @see TuningService
     */
    public void delete(TuningService  service)
    {
        dbOperations.deleteEntity(service);
    }
    /**
     * Метод возвращет запись из сущности <strong>ТЮНИНГ_УСЛУГА</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>ТЮНИНГ_УСЛУГА</strong>, найденная по ИД
     * @see TuningService
     */
    public TuningService getByID(long id)
    {
        return (TuningService)dbOperations.findEntityById(TuningService.class, id);
    }
}