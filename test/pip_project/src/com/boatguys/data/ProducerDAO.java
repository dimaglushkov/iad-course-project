package com.boatguys.data;

import com.boatguys.entities.Producer;

import java.util.List;

/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий CRUD доступ к сущности <strong>ПРОИЗВОДИТЕЛЬ</strong>
 * @see Producer
 */
public class ProducerDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public ProducerDAO()
    {
        dbOperations = new DBOperations();
    }
    /**
     * Метод позволяет добавить или измениьть запись в сущности <strong>ПРОИЗВОДИТЕЛЬ</strong>
     * @param producer обьект класса Producer (в БД сущность <strong>ПРОИЗВОДИТЕЛЬ</strong>)
     * @see Producer
     */
    public void save(Producer producer)
    {
        dbOperations.createEntity(producer);
    }

    public void update(Producer producer)
    {
        dbOperations.updateEntity(producer);
    }

    /**
     * Мктод позволяет удалить запись в сущности <strong>ПРОИЗВОДИТЕЛЬ</strong>
     * @param producer обьект класса Producer (в БД сущность <strong>ПРОИЗВОДИТЕЛЬ</strong>)
     * @see Producer
     */
    public void delete(Producer  producer)
    {
        dbOperations.deleteEntity(producer);
    }
    /**
     * Метод возвращет запись из сущности <strong>ПРОИЗВОДИТЕЛЬ</strong> (select по ИД)
     * @param id Уникальный идентификатор для каждой записи
     * @return Запись из сущности <strong>ПРОИЗВОДИТЕЛЬ</strong>, найденная по ИД
     * @see Producer
     */
    public Producer getByID(long id)
    {
        return (Producer)dbOperations.findEntityById(Producer.class, id);
    }

    public Producer getByEmail(String email)
    {
        List<Producer> producers = dbOperations.queryWithCondition(Producer.class, String.class, email, "email");

        if(producers.size() > 1)
            throw new IllegalStateException("More than one producer with the email!");
        else if( producers.size() == 0)
            return null;

        return producers.get(0);
    }

    public List<Producer> getAll()
    {
        return dbOperations.selectAll(Producer.class);
    }
}