package com.boatguys.data;

import com.boatguys.entities.Track;

import java.util.List;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ТРАССЫ</strong>
 * @see Track
 */
public class TrackDAO
{
    private  DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public TrackDAO()
    {            
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>ТРАССЫ</strong>
     * @param track обьект класса Track (в БД сущность <strong>ТРАССЫ</strong>)
     * @see Track
     */
    public void save(Track track)
    {
        dbOperations.createEntity(track);
    }

    public void update(Track track)
    {
        dbOperations.updateEntity(track);
    }

    /**
     * Мктод позволяет удалить запись в сущности <strong>ТРАССЫ</strong>
     * @param track обьект класса Producer (в БД сущность <strong>ТРАССЫ</strong>)
     * @see Track
     */
    public void delete(Track  track)
    {
        dbOperations.deleteEntity(track);
    }
    /**
     * Метод возвращет запись из сущности <strong>ТРАССЫ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>ТРАССЫ</strong>, найденная по ИД
     * @see Track
     */
    public Track getByID(long id)
    {
        return (Track)dbOperations.findEntityById(Track.class, id);
    }

    /**
     * Метод возвращет записи из сущности <strong>ТРАССЫ</strong> (select по СЛОЖНОСТЬ)
     * @param complexity Сложность нужных трасс
     * @return Запись/Записи из сущности <strong>ТРАССЫ</strong>, найденная по СЛОЖНОСТЬ
     * @see Track
     */
    public List<Track> getTracksForTheComplexity(int complexity)
    {
        return dbOperations.queryWithCondition(Track.class, Integer.class, complexity, "complexity");
    }
    /**
     * Метод возвращет записи из сущности <strong>ТРАССЫ</strong> (select по МЕСТО_ПРОВЕДЕНИЯ)
     * @param location Место где находится трасса
     * @return Запись/Записи из сущности <strong>ТРАССЫ</strong>, найденная по МЕСТО_ПРОВЕДЕНИЯ
     * @see Track
     */
    public List<Track> getTracksForTheLocation(String location)
    {
        return dbOperations.queryWithCondition(Track.class, String.class, location, "location");
    }
    /**
     * Метод возвращет записи из сущности <strong>ТРАССЫ</strong> (select по ТИП)
     * @param type Место по типу трассы
     * @return Запись/Записи из сущности <strong>ТРАССЫ</strong>, найденная по ТИП
     * @see Track
     */
    public List<Track> getTracksForTheType(String type)
    {
        return dbOperations.queryWithCondition(Track.class, String.class, type, "type");
    }
}