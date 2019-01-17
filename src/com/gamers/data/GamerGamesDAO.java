package com.gamers.data;

import com.gamers.entities.GamerGames;
import com.gamers.entities.User;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Игры_геймера</strong>
 * @see GamerGames
 */
public class GamerGamesDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public GamerGamesDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Игры_геймера</strong>
     * @param gamergames обьект класса GamerGames (в БД сущность <strong>Г_Игры_геймера</strong>)
     * @see GamerGames
     */
    public void save(GamerGames gamergames)
    {
        dbOperations.createOrUpdateEntity(gamergames);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Игры_геймера</strong>
     * @param gamergames обьект класса GamerGames (в БД сущность <strong>Г_Игры_геймера</strong>)
     * @see GamerGames
     */
    public void delete(GamerGames gamergames)
    {
        dbOperations.deleteEntity(gamergames);
    }


    /**
     * Метод возвращет все записи из сущности <strong>Г_Игры_геймера</strong> (select * ...)
     * @return Возвращает List всех записей из сущности <strong>Г_Игры_геймера</strong>
     * @see GamerGames
     */
    public List<GamerGames> getAll()
    {
        return dbOperations.selectAll(GamerGames.class);
    }
}