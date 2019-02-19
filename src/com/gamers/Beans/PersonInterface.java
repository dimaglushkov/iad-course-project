package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Local
public interface PersonInterface
{

    JSONObject register(String email, String password, String name);

}
