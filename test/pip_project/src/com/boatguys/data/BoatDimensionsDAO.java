package com.boatguys.data;

import com.boatguys.entities.BoatDimensions;
import com.boatguys.entities.BaseBoat;

import java.util.List;

/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Габариты</strong>
 * @see BoatDimensions
 */
public class BoatDimensionsDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public BoatDimensionsDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Габариты</strong>
     * @param boatDimension обьект класса BoatDimensions (в БД сущность <strong>Габариты</strong>)
     * @see BoatDimensions
     */
    public void save(BoatDimensions boatDimension)
    {
        dbOperations.createEntity(boatDimension);
    }

    public void update(BoatDimensions boatDimensions)
    {
        dbOperations.updateEntity(boatDimensions);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Габариты</strong>
     * @param boatDimension обьект класса BoatDimensions (в БД сущность <strong>Габариты</strong>)
     * @see BoatDimensions
     */
    public void delete(BoatDimensions boatDimension)
    {
        dbOperations.deleteEntity(boatDimension);
    }

    /**
     * Метод возвращает габариты для базовой лодки
     * @param baseBoat Обьект класса Boat
     * @see Boat
     * @return Запись из сущности <strong>Габариты</strong>, найденная по ИД базовой лодки
     * @see BoatDimensions
     */
    public BoatDimensions getForBaseBoat(BaseBoat baseBoat)
    {
        List<BoatDimensions> dimensions = dbOperations.queryWithCondition(BoatDimensions.class, BaseBoat.class, baseBoat, "baseBoat");

        if(dimensions.size() > 1)
            throw new IllegalStateException("There are more than one set of dimensions for base boat");
        else if(dimensions.size() == 0)
            return null;

        return dimensions.get(0);
    }
    /**
     * Метод возвращет все записи из сущности <strong>Габариты</strong> (select * ...)
     * @return Возвращает List всех запичей из сущности <strong>Габариты</strong>
     * @see BoatDimensions
     */
    public List<BoatDimensions> getAll()
    {
        return dbOperations.selectAll(BoatDimensions.class);
    }
}