package com.gamers.Beans;

import com.gamers.DAO.PersonDAO;
import com.gamers.DAO.ReportDAO;
import com.gamers.Entities.Person;
import com.gamers.Entities.Report;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
//import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Session;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("report")
@Stateless
@Local(ReportInterface.class)
public class ReportBean implements ReportInterface
{

    @Resource
    SessionContext sessionContext;

    @Inject
    ReportNotificationSender reportNotificationSender = new ReportNotificationSender();

    private ReportDAO reportDAO = new ReportDAO();
    private PersonDAO personDAO = new PersonDAO();

    @POST
    @Path("/new")
    @Produces("application/json")
    @Override
    public JSONObject createReport(@FormParam("topic") String topic,
                                   @FormParam("description") String description)
    {
        JSONObject responseJSON = new JSONObject();

        Person curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        Report report = new Report();
        report.setPerson(curPerson);
        report.setTopic(topic);
        report.setDescription(description);

        if (!reportDAO.create(report))
        {
            responseJSON.put("success", "false");
            responseJSON.put("description", "Unable to create new report.");
            return responseJSON;
        }

        reportNotificationSender.notify(topic, sessionContext.getCallerPrincipal().getName());

        responseJSON.put("success", "true");
        responseJSON.put("description", "Report created!");
        return responseJSON;
    }
}
