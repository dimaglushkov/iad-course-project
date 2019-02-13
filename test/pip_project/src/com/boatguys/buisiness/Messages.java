package com.boatguys.buisiness;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;

@Local
public interface Messages
{

    @RolesAllowed({"users", "administrators", "moderators"})
    String getDialogWithUser(String username);


    @RolesAllowed({"users", "administrators", "moderators"})
    void send(String message, long id);


    @RolesAllowed({"users", "administrators", "moderators"})
    String getLastUserMessages();
}
