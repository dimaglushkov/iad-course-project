package com.gamers.data;

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
    
    private PersistenceManager() 
    {
    }
   
    public EntityManagerFactory getEntityManagerFactory() 
    {
      
      if (entityManagerFactory == null)
        createEntityManagerFactory();

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
    
    private void createEntityManagerFactory() 
    {      
      this.entityManagerFactory = Persistence.createEntityManagerFactory("hibernate_manager");
    }

  }