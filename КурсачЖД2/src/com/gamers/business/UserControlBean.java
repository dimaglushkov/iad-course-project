package com.gamers.business;

import com.gamers.data.UserDAO;
import com.gamers.entities.Group;
import com.gamers.entities.User;
import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Local(UserControl.class)
public class UserControlBean implements UserControl
{
    private UserDAO userDAO = new UserDAO();

    private Group bannedGroup = new Group("banned-users");
    private Group adminGroup = new Group("administrators");
    private Group moderGroup = new Group("moderators");
    private Group userGroup = new Group("users");
    private Group producerGroup = new Group("producers");

    private Gson gson = new Gson();

    private Mail mail = new Mail();

    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getAllUsers()
    {
        List<User> users = userDAO.getAll();

        Map<Long, String> usersEmails = new HashMap<>();

        for(User u : users)
            usersEmails.put(u.getId(), u.getEmail());

        return gson.toJson(usersEmails);
    }

    @RolesAllowed({"administrators"})
    @Override
    public void banUser(String username)
    {
        User user = userDAO.getByEmail(username);

        user.getGroups().clear();
        user.addGroup(bannedGroup);

        userDAO.update(user);

        mail.send("Banned", "You was banned", user.getEmail());
    }

    @RolesAllowed({"administrators"})
    @Override
    public void unbanUser(String username)
    {
        User user = userDAO.getByEmail(username);

        user.getGroups().clear();
        user.addGroup(userGroup);

        userDAO.update(user);

        mail.send("Unbanned", "You was unbanned", user.getEmail());
    }

    @RolesAllowed({"administrators"})
    @Override
    public void grantRights(String username, String groupName)
    {
        if(!(groupName.equals("administrators") || groupName.equals("moderators") || groupName.equals("producers")))
            throw new IllegalArgumentException("No such group");

        User user = userDAO.getByEmail(username);

        if(groupName.equals("administrators"))
            user.addGroup(adminGroup);
        else if(groupName.equals("moderators"))
            user.addGroup(moderGroup);
        else if(groupName.equals("producers"))
            user.addGroup(producerGroup);

        userDAO.update(user);
    }

    @RolesAllowed({"administrators"})
    @Override
    public void takeRightsAway(String username, String groupName)
    {
        if(!(groupName.equals("administrators") || groupName.equals("moderators") || groupName.equals("producers")))
            throw new IllegalArgumentException("No such group");

        User user = userDAO.getByEmail(username);

        if(groupName.equals("administrators"))
            user.getGroups().remove(adminGroup);
        else if(groupName.equals("moderators"))
            user.getGroups().remove(moderGroup);
        else if(groupName.equals("producers"))
            user.getGroups().remove(producerGroup);

        userDAO.update(user);
    }
}
