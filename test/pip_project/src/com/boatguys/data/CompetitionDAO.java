package com.boatguys.data;

import com.boatguys.entities.Competition;
import com.boatguys.entities.Track;

import java.util.List;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>СОРЕВНОВАНИЯ</strong>
 * @see Competition
 */
public class CompetitionDAO
{
    private DBOperations dbOperations;

    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public CompetitionDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>СОРЕВНОВАНИЯ</strong>
     * @param competition обьект класса Competition (в БД сущность <strong>СОРЕВНОВАНИЯ</strong>)
     * @see Competition
     */
    public void save(Competition competition)
    {
        dbOperations.createEntity(competition);
    }

    public void update(Competition competition)
    {
        dbOperations.updateEntity(competition);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>СОРЕВНОВАНИЯ</strong>
     * @param competition обьект класса BoatDimensions (в БД сущность <strong>СОРЕВНОВАНИЯ</strong>)
     * @see Competition
     */
    public void delete(Competition competition)
    {
        dbOperations.deleteEntity(competition);
    }
    /**
     * Метод возвращет запись из сущности <strong>СОРЕВНОВАНИЯ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>СОРЕВНОВАНИЯ</strong>, найденная по ИД
     * @see Competition
     */
    public Competition getByID(long id) 
    {
        return (Competition)dbOperations.findEntityById(Competition.class, id);
    }

    /**
     * Метод возвращает запись из из сущности <strong>СОРЕВНОВАНИЯ</strong> с оределенным уронем соревнований
     * @param level Уровень соревнования
     * @return Запись из сущности <strong>СОРЕВНОВАНИЯ</strong>, найденная по УРОВЕНЬ_СОРЕВНОВАНИЙ
     * @see Competition
     */
    public List<Competition> getAllForTheLevel(String level)
    {
        return dbOperations.queryWithCondition(Competition.class, String.class, level, "level");
    }
    /**
     * Метод возвращает запись из из сущности <strong>СОРЕВНОВАНИЯ</strong> с  оределенным типом соревнований
     * @param type тип соревнования
     * @return Запись из сущности <strong>СОРЕВНОВАНИЯ</strong>, найденная по ТИП
     * @see Competition
     */
    public List<Competition> getAllForTheType(String type)
    {
        return dbOperations.queryWithCondition(Competition.class, String.class, type, "type");
    }
    /**
     * Метод возвращает запись из из сущности <strong>СОРЕВНОВАНИЯ</strong> с оределенным ИД_ТРАССЫ
     * @param track Уникальный идентификатор трассы
     * @return Запись из сущности <strong>СОРЕВНОВАНИЯ</strong>, найденная по ИД_ТРАССЫ
     * @see Competition
     */
    public List<Competition> getAllForTheTrack(Track track)
    {
        return dbOperations.queryWithCondition(Competition.class, Track.class, track, "track");
    }

    public List<Competition> getAll()
    {
        return dbOperations.selectAll(Competition.class);
    }
}