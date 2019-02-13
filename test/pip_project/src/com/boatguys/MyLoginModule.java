package com.boatguys;

import com.boatguys.data.UserDAO;
import com.boatguys.entities.User;
import com.sun.appserv.security.AppservPasswordLoginModule;
import javax.security.auth.login.LoginException;
import java.util.stream.Collectors;

public class MyLoginModule extends AppservPasswordLoginModule
{
    private UserDAO userDAO = new UserDAO();

    public MyLoginModule()
    {
    }

    protected void authenticateUser() throws LoginException
    {
        if (this._username != null && this._password != null)
        {
            User user = userDAO.getByEmailAndPassword(_username, _password);

            if (user == null)
            {
                throw new LoginException("bad login/password");
            } else
            {
                String[] userGroups =  user.getGroups().stream().map(usr -> usr.getGroupName()).toArray(String[]::new );

                this.commitUserAuthentication(userGroups);
            }
        } else
        {
            throw new LoginException("Username of password is null");
        }
    }
}