package com.gamers.data;

import com.gamers.entities.Game;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.1
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Игры</strong>
 * @see Game
 */
public class GameDAO
{
    private DBOperations dbOperations;
    
    public GameDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Игры</strong>
     * @param game обьект класса Game (в БД сущность <strong>Г_Игры</strong>)
     * @see Game
     */
    public void save(Game game)
    { 
        dbOperations.createOrUpdateEntity(game);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Игры</strong>
     * @param game обьект класса Game (в БД сущность <strong>Г_Игры</strong>)
     * @see Game
     */
    public void delete(Game game)
    {
        dbOperations.deleteEntity(game);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Игры</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждй записи
     * @return Запись из сущности <strong>Г_Игры</strong>, найденная по ИД
     */
    public Game getById(long id)
    {
        return (Game)dbOperations.findEntityById(Game.class, id);
    }
    /**
     * Метод возвращет все записи из сущности <strong>Г_Игры</strong> (select * ...)
     * @return Возвращает List всех записей из сущности <strong>Г_Игры</strong>
     */
    public List<Game> getAll()
    {
        return dbOperations.selectAll(Game.class);
    }
}