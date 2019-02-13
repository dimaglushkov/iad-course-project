package com.boatguys.buisiness;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;

@Local
public interface UserControl
{

    @RolesAllowed({"users", "administrators", "moderators"})
    String getAllUsers();


    @RolesAllowed({"administrators"})
    void banUser(String username);


    @RolesAllowed({"administrators"})
    void unbanUser(String username);

    @RolesAllowed({"administrators"})
    void grantRights(String username, String groupName);


    @RolesAllowed({"administrators"})
    void takeRightsAway(String username, String groupName);
}
