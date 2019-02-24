package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface GameInterface
{
    //TODO: get library

    @RolesAllowed("admin")
    JSONObject createGame(String name, String date, String description);

    @RolesAllowed("admin")
    JSONObject deleteGame(long id);

    @RolesAllowed({"admin", "user"})
    JSONObject addToLib(long gameId);

    @RolesAllowed({"admin", "user"})
    JSONObject removeFormLib(long gameId);

    @RolesAllowed({"admin", "user"})
    JSONObject getUsersGames(String nickname);

    @RolesAllowed({"admin", "user"})
    JSONObject getGameInfo(long gameId);

}
