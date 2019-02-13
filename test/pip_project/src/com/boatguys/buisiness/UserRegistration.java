package com.boatguys.buisiness;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ws.rs.*;

@Local
public interface UserRegistration
{
    void register(String email,
                  String password,
                  String name);


    String hasUserWithSuchEmail(String email);

}
