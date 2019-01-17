package com.gamers.business;

import com.gamers.data.UserDAO;
import com.gamers.entities.User;
import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Stateful
@Local
public class UserProfileBean implements UserProfile
{
    @Resource
    private SessionContext context;

    private UserDAO userDAO = new UserDAO();

    private User currentUser;
    private Gson gson = new Gson();

    public UserProfileBean()
    {
        currentUser = userDAO.getByEmail(context.getCallerPrincipal().getName());
    }

    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getInformation()
    {
        Map<String, String> userInfo = new HashMap<>();

        userInfo.put("name", currentUser.getName());
        userInfo.put("email", currentUser.getEmail());
        userInfo.put("id", currentUser.getId() + "");
        userInfo.put("gender", currentUser.getGender() + "");
        userInfo.put("birthday", currentUser.getBirthday().toString());
        userInfo.put("sportRate", currentUser.getSportRate() + "");
        userInfo.put("rate", currentUser.getRate() + "");

        String userGroups = gson.toJson(currentUser.getGroups());

        userInfo.put("groups", userGroups);

        return gson.toJson(userInfo);
    }

    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void change(String name, Date birthday, String gender, String password)
    {
        if(!(gender.equals("М") || gender.equals("Ж")))
            throw new IllegalArgumentException("No such gender! We support only classic genders");

        currentUser.setName(name);
        currentUser.setBirthday(birthday);
        currentUser.setGender(gender.charAt(0));
        currentUser.setPassword(password);

        userDAO.update(currentUser);
    }

}



