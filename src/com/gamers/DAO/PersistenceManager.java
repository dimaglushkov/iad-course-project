package com.gamers.DAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager 
{    
    private static final PersistenceManager persistenceManager = new PersistenceManager();
    protected EntityManagerFactory entityManagerFactory;
    
    public static PersistenceManager getInstance() 
    {
      return persistenceManager;
    }
    
    private PersistenceManager() {
    }
   
    public EntityManagerFactory getEntityManagerFactory()
    {
      
      if (entityManagerFactory == null)
          entityManagerFactory = Persistence.createEntityManagerFactory("iad-unit");

      return entityManagerFactory;
    }
    
    public void closeEntityManagerFactory()
    {
      if (entityManagerFactory != null) 
      {
        entityManagerFactory.close();
        entityManagerFactory = null;
      }

    }

  }