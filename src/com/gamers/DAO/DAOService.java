package com.gamers.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class DAOService<T, PK extends Serializable> {

    private EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory();

    private Class<T> type;

    public DAOService() {
    }


    public DAOService(Class <T> type) {
        this.type=type;
    }

    public void finishWork() {
        entityManagerFactory.close();
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void create(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public T findById(PK id) throws EntityNotFoundException {
        EntityManager entityManager = getEntityManager();
        T entity = entityManager.find(type, id);

        entityManager.close();
        if (entity == null)
            throw new EntityNotFoundException("No user with such id");

        return entity;
    }

    public void delete(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        //entityManager.remove(entity);
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<T> findAll() {
        EntityManager entityManager = getEntityManager();
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(type);
        criteria.select(criteria.from(type));
        List<T> entities = entityManager.createQuery(criteria).getResultList();
        entityManager.close();
        return entities;
    }

    public void deleteAll() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        List<T> entityList = findAll();
        for (T entity : entityList) {
            delete(entity);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}