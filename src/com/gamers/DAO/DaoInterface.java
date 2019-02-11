package com.gamers.DAO;

import java.io.Serializable;
import java.util.List;

public interface DaoInterface<T, PK extends Serializable> {

    void create(T entity);

    void update(T entity);

    T findById(PK id);

    void delete(T entity);

    List<T> selectAll();

    void deleteAll();

}