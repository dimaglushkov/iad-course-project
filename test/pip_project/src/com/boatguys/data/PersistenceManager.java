package com.boatguys.data;

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
    
    private PersistenceManager() 
    {
    }
   
    public EntityManagerFactory getEntityManagerFactory(String username, String password)
    {
      
      if (entityManagerFactory == null)
        createEntityManagerFactory(username, password);

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
    
    private void createEntityManagerFactory(String username, String password)
    {
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("hibernate_manager", properties);
    }

  }