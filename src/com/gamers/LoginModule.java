package com.gamers;

import com.gamers.Entities.Person;
import com.gamers.Services.PersonService;
import com.sun.appserv.security.AppservPasswordLoginModule;
import javax.security.auth.login.LoginException;
import java.util.stream.Collectors;

public class LoginModule extends AppservPasswordLoginModule
{
    private PersonService personService = new PersonService();

    public LoginModule()
    {
    }

    protected void authenticateUser() throws LoginException
    {
        if (this._username != null && this._password != null)
        {
            Person person = personService.findByNicknameAndPassword(_username, _password);

            if (person == null)
            {
                throw new LoginException("Bad nickname or password!");
            } else
            {
                String[] userGroups =  person.getGroups().stream().map(usr -> usr.getGroupName()).toArray(String[]::new );

                this.commitUserAuthentication(userGroups);
            }
        } else
        {
            throw new LoginException("Enter your nickname and password!");
        }
    }
}