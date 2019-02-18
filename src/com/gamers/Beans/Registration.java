package com.gamers.Beans;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ws.rs.*;

@Local
public interface Registration
{

    void register(String email, String password, String name);

}
