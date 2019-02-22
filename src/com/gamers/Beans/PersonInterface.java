package com.gamers.Beans;

import org.json.simple.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Local
public interface PersonInterface
{

    JSONObject register(String email, String password, String name);

    @RolesAllowed({"user", "admin", "banned-user"})
    void logout(HttpServletResponse response, HttpServletRequest request) throws IOException;

    @RolesAllowed({"user", "admin"})
    JSONObject account(String nickname);

}
