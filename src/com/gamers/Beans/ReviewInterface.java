package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;

public interface ReviewInterface
{
    @RolesAllowed({"admin", "user"})
    void create(String gameId, String rate,  String description);

    @RolesAllowed({"admin", "user"})
    JSONObject getByNickname(String nickname);

    @RolesAllowed({"admin", "user"})
    JSONObject getByGameId(String gameId);

}
