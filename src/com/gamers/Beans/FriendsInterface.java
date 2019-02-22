package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.PathParam;

@Local
public interface FriendsInterface
{

    @RolesAllowed({"admin", "user"})
    JSONObject addFriend(String nickname);

    @RolesAllowed({"admin", "user"})
    void removeFriend(String nickname);

    @RolesAllowed({"admin", "user"})
    void acceptRequest(String nickname);

    @RolesAllowed({"admin", "user"})
    void declineRequest(String nickname);

    @RolesAllowed({"user", "admin"})
    JSONObject getFriends(String nickname);

}
