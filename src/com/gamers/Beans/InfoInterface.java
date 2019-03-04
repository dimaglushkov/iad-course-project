package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface InfoInterface
{
    @RolesAllowed({"admin", "user"})
    JSONObject setInfo(String name, String surname, String birthDate, String country, String city);

    @RolesAllowed({"admin", "user"})
    JSONObject getInfo(String nickname);

}
