package com.gamers.DAO;

import com.gamers.Entities.Group;
import com.gamers.Entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class GroupDAO extends DAOService<Group, Long>
{

    public GroupDAO()
    {
        super(Group.class);
    }

    public void updateBanStatus(Person person, Group group)
    {
        PersonDAO personDAO = new PersonDAO();

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(
                "DELETE FROM ГРУППА_ЛИЧН " +
                        "WHERE НИКНЕЙМ = '" + person.getNickname()  + "';", Group.class);
        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

        group.setNickname(person.getNickname());

        person.getGroups().clear();
        person.addGroup(group);
        create(group);

    }

    public void addGroup(Person person, Group group)
    {
        group.setNickname(person.getNickname());
        create(group);
        person.addGroup(group);
    }

    public void removeGroup(Person person, Group group)
    {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(
                "DELETE FROM ГРУППА_ЛИЧН " +
                        " WHERE НИКНЕЙМ = '" + person.getNickname()  +
                        "' AND ГРУППА = '" + group.getGroupName() + "';", Group.class);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.close();
        person.getGroups().remove(group);

    }

}
