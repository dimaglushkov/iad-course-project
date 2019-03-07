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
    JSONObject removeFriend(String nickname);

    @RolesAllowed({"admin", "user"})
    JSONObject acceptRequest(String nickname);

    @RolesAllowed({"admin", "user"})
    JSONObject declineRequest(String nickname);

    @RolesAllowed({"user", "admin"})
    JSONObject getFriends(String nickname);

    @RolesAllowed({"admin", "user"})
    JSONObject getRequests();

}
