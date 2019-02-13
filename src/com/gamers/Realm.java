package com.gamers;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.sun.enterprise.security.util.IASSecurityException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Enumeration;
import java.util.List;

public class Realm extends AppservRealm
{

    @Override
    public String getAuthType()
    {
        return "Authentication throw Data Base";
    }

    @Override
    public Enumeration getGroupNames(String s) throws InvalidOperationException, NoSuchUserException
    {

        return null;
    }

    @Override
    public Enumeration getUserNames() throws BadRealmException
    {
        return super.getUserNames();
    }

    @Override
    public void refresh() throws BadRealmException
    {
        super.refresh();
    }

    @Override
    public void addUser(String name, char[] password, String[] groupList) throws BadRealmException, IASSecurityException
    {
        super.addUser(name, password, groupList);
    }

    @Override
    public void removeUser(String name) throws NoSuchUserException, BadRealmException
    {
        super.removeUser(name);
    }

    @Override
    public void updateUser(String name, String newName, char[] password, String[] groups) throws NoSuchUserException, BadRealmException, IASSecurityException
    {
        super.updateUser(name, newName, password, groups);
    }

    @Override
    public boolean supportsUserManagement()
    {
        return super.supportsUserManagement();
    }

    @Override
    public void persist() throws BadRealmException
    {
        super.persist();
    }
}