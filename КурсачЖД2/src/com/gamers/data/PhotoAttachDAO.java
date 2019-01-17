package com.gamers.data;

import com.gamers.entities.PhotoAttach;

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
 * Это класс обеспечивающий CRUD доступ к сущности <strong>Г_Прикрепление_фотографии</strong>
 * @see PhotoAttach
 */
public class PhotoAttachDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public PhotoAttachDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>Г_Прикрепление_фотографии</strong>
     * @param attach обьект класса PhotoAttach (в БД сущность <strong>Г_Прикрепление_фотографии</strong>)
     * @see PhotoAttach
     */
    public void save(PhotoAttach attach)
    {
        dbOperations.createOrUpdateEntity(attach);
    }
    /**
     * Мктод позволяет удалить запись в сущности <strong>Г_Прикрепление_фотографии</strong>
     * @param attach обьект класса PhotoAttach (в БД сущность <strong>Г_Прикрепление_фотографии</strong>)
     * @see PhotoAttach
     */
    public void delete(PhotoAttach  attach)
    {
        dbOperations.deleteEntity(attach);
    }
    /**
     * Метод возвращет запись из сущности <strong>Г_Прикрепление_фотографии</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>Г_Прикрепление_фотографии</strong>, найденная по ИД
     * @see PhotoAttach
     */
    public PhotoAttach getByID(long id)
    {
        return (PhotoAttach)dbOperations.findEntityById(PhotoAttach.class, id);
    }
}