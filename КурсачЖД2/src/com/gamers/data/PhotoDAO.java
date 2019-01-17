package com.gamers.data;
import com.gamers.entities.Photo;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
/**
 * @author Глушков Дмитрий, Черноусов Евгений
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Фотография</strong>
 * @see Photo
 */
public class PhotoDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public PhotoDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Фотография</strong>
     * @param photo обьект класса Photo (в БД сущность <strong>Г_Фотография</strong>)
     * @see Photo
     */
    public void save(Photo photo)
    {
        dbOperations.createOrUpdateEntity(photo);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Фотография</strong>
     * @param photo обьект класса Photo (в БД сущность <strong>Г_Фотография</strong>)
     * @see Photo
     */
    public void delete(Photo  photo)
    {
        dbOperations.deleteEntity(photo);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Фотография</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Фотография</strong>, найденная по ИД
     * @see Photo
     */
    public Photo getByID(long id)
    {
        return (Photo)dbOperations.findEntityById(Photo.class, id);
    }
    /**
     * Метод возвращает запись из из сущности <strong>Г_Фотография</strong> с определенным названием
     * @param name Название картинки
     * @return Запись из сущности <strong>Г_Фотография</strong>, найденная по Названию
     * @see Photo
     */
    public List<Photo> getAllForTheType(String name)
    {
        return dbOperations.queryWithCondition(Photo.class, String.class, name, "type");
    }

}