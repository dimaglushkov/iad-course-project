package com.boatguys.buisiness;

import com.boatguys.data.UserDAO;
import com.boatguys.entities.Group;
import com.boatguys.entities.User;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.*;

@Path("users")
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

    private Gson gsonWithExclusion = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy()
    {
        @Override
        public boolean shouldSkipField(FieldAttributes f)
        {
            return (f.getDeclaringClass() == User.class &&
                    ( f.getName().equals("password") || f.getName().equals("boats")));
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass)
        {
            return false;
        }
    }).create();

    private Mail mail = new Mail();

    @GET
    @Produces("application/json")
    @Path("/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getAllUsers()
    {
        List<User> users = userDAO.getAll();

        users.sort(Comparator.comparingInt(User::getRate));

        return gsonWithExclusion.toJson(users);
    }

    @POST
    @Path("/ban/{username}")
    @RolesAllowed({"administrators"})
    @Override
    public void banUser(@PathParam("username") String username)
    {
        User user = userDAO.getByEmail(username);

        user.getGroups().clear();
        user.addGroup(bannedGroup);

        userDAO.update(user);

        mail.send("Banned", "You was banned", user.getEmail());
    }

    @POST
    @Path("/unban/{username}")
    @RolesAllowed({"administrators"})
    @Override
    public void unbanUser(@PathParam("username") String username)
    {
        User user = userDAO.getByEmail(username);

        user.getGroups().clear();
        user.addGroup(userGroup);

        userDAO.update(user);

        mail.send("Unbanned", "You was unbanned", user.getEmail());
    }

    @POST
    @Path("/grant/{username}/{groupName}")
    @RolesAllowed({"administrators"})
    @Override
    public void grantRights(@PathParam("username") String username,@PathParam("groupName") String groupName)
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

    @POST
    @Path("/ungrant/{username}/{groupName}")
    @RolesAllowed({"administrators"})
    @Override
    public void takeRightsAway(@PathParam("username") String username,@PathParam("groupName") String groupName)
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
