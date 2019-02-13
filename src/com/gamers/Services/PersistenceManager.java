package com.gamers.Services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

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