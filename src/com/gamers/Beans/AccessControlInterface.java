package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface AccessControlInterface
{

    @RolesAllowed("admin")
    JSONObject unbanPerson(String nickname);

    @RolesAllowed("admin")
    JSONObject banPerson(String nickname);

    @RolesAllowed("admin")
    JSONObject grantRights(String nickname, String groupname);

    @RolesAllowed("admin")
    JSONObject ungrantRights(String nickname, String groupname);

    @RolesAllowed({"admin", "user"})
    JSONObject checkAdminRights();


}
