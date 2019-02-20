package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface FriendsInterface
{

    @RolesAllowed({"admin", "user"})
    JSONObject sendRequest(String nickname);

    @RolesAllowed({"admin", "user"})
    void acceptRequest(String nickname);

    @RolesAllowed({"admin", "user"})
    void declineRequest(String nickname);


}
