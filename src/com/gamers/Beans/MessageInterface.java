package com.gamers.Beans;


import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface MessageInterface
{
    @RolesAllowed({"admin", "user"})
    JSONObject send(String receiver, String topic, String text);

    @RolesAllowed({"admin", "user"})
    JSONObject getBySender();

    @RolesAllowed({"admin", "user"})
    JSONObject getByReceiver();


}
