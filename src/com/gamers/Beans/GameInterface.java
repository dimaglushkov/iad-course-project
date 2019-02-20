package com.gamers.Beans;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface GameInterface
{

    @RolesAllowed("admin")
    void createGame(String name, int year, String description);

    @RolesAllowed("admin")
    void deleteGame(String name);

    @RolesAllowed("admin")
    void updateGame(String name);

}
