package com.gamers.data;

import com.gamers.entities.Dish;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import javax.persistence.*;
import javax.persistence.criteria.*;

/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Блюдо</strong>
 * @see Dish
 */
public class DishDAO
{
    private DBOperations dbOperations;
    
    public DishDAO()
    {
        dbOperations = new DBOperations();
    }

    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Блюдо</strong>
     * @param dish обьект класса Dish (в БД сущность <strong>Г_Блюдо</strong>)
     */
    public void save(Dish dish)
    {
        dbOperations.createOrUpdateEntity(dish);
    }

    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Блюдо</strong>
     * @param dish обьект класса Dish (в БД сущность <strong>Г_Блюдо</strong>)
     * @see Dish
     */
    public void delete(Dish dish)
    {
        dbOperations.deleteEntity(dish);
    }

    /**
     * Метод возвращет запись из сущности <strong>Г_Блюдо</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>Г_Блюдо</strong>, найденная по ИД
     * @see Dish
     */
    public Dish getById(long id)
    {
        return (Dish)dbOperations.findEntityById(Dish.class, id);
    }

    /**
     * Метод возвращет все записи из сущности <strong>Г_Блюдо</strong> (select * ...)
     * @return Возвращает List всех запиcей из сущности <strong>Г_Блюдо</strong>
     * @see Dish
     */
    public List<Dish> getAll()
    {
        return dbOperations.selectAll(Dish.class);
    }

}