package com.gamers.Beans;

import org.json.simple.JSONObject;

//import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

@Local
public interface ReportInterface
{
    //@RolesAllowed({"user", "admin", "banned-user"})
    public JSONObject createReport(String topic, String description);
}
