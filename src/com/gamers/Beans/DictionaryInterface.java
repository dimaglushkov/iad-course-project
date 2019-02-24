package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface DictionaryInterface
{

    @RolesAllowed({"admin", "user"})
    JSONObject create(String topic, String text);

    @RolesAllowed({"admin", "user"})
    JSONObject getAll();

    @RolesAllowed({"admin", "user"})
    JSONObject getAllByTopic(String topic);

    @RolesAllowed({"admin", "user"})
    JSONObject delete(long id);

}
