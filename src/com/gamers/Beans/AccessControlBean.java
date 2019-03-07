package com.gamers.Beans;


import com.gamers.DAO.GroupDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Group;
import com.gamers.Entities.Person;
import org.json.simple.JSONObject;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.xml.registry.infomodel.User;

@Path("access")
@Stateless
@Local(AccessControlInterface.class)
public class AccessControlBean implements AccessControlInterface
{
    @Resource
    SessionContext sessionContext;

    private PersonDAO personDAO = new PersonDAO();
    private JSONObject response;
    private GroupDAO groupDAO = new GroupDAO();
    private Group bannedGroup = new Group("banned-user");

    @GET
    @Path("check")
    @RolesAllowed({"admin", "user"})
    @Produces("application/json")
    @Override
    public JSONObject checkAdminRights()
    {
        response = new JSONObject();
        response.put("admin", groupDAO.isAdmin(sessionContext.getCallerPrincipal().getName()));
        return initResponse(true, "checked successfully");
    }


    @POST
    @Path("{nickname}/ban")
    @RolesAllowed("admin")
    @Produces("application/json")
    @Override
    public JSONObject banPerson(@PathParam("nickname") String nickname)
    {

        response = new JSONObject();
        Person person = personDAO.findByNickname(nickname);

        if (person == null)
            return initResponse(false, "Wrong nickname");

        groupDAO.updateBanStatus(person, bannedGroup);


        return initResponse(true, "Person with nickname " + nickname +" banned");
    }

    @POST
    @Path("/{nickname}/unban")
    @RolesAllowed("admin")
    @Produces("application/json")
    @Override
    public JSONObject unbanPerson(@PathParam("nickname")String nickname)
    {

        Person person = personDAO.findByNickname(nickname);
        response = new JSONObject();
        if (person == null)
            return initResponse(false, "Wrong nickname");

        groupDAO.updateBanStatus(person, new Group("user"));

        return initResponse(true, "Person with nickname " + nickname +" unbanned");
    }


    @POST
    @Path("/{nickname}/grant/{groupname}")
    @RolesAllowed("admin")
    @Produces("application/json")
    @Override
    @SuppressWarnings("Duplicates")
    public JSONObject grantRights(@PathParam("nickname") String nickname,
                                  @PathParam("groupname") String groupName)
    {
        response = new JSONObject();
        if(!(groupName.equals("admin") || (groupName.equals("user"))))
            return initResponse(false, "Wrong groupname");

        Person person = personDAO.findByNickname(nickname);
        if (person == null)
            return initResponse(false, "Wrong nickname");

        groupDAO.addGroup(person, new Group(groupName));

        return initResponse(true, nickname + " added to " + groupName + " group");
    }

    @POST
    @Path("/{nickname}/ungrant/{groupname}")
    @RolesAllowed("admin")
    @Produces("application/json")
    @SuppressWarnings("Duplicates")
    @Override
    public JSONObject ungrantRights(@PathParam("nickname") String nickname,
                                    @PathParam("groupname") String groupName)
    {
        response = new JSONObject();
        if(!(groupName.equals("admin") || (groupName.equals("user"))))
            return initResponse(false, "Wrong groupname");

        Person person = personDAO.findByNickname(nickname);
        if (person == null)
            return initResponse(false, "Wrong nickname");

        groupDAO.removeGroup(person, new Group(groupName));

        return initResponse(true, nickname + " no longer has " + groupName + " rights");
    }


    private JSONObject initResponse(boolean success, String desc)
    {
        response.put("success", success);
        response.put("description", desc);
        return response;
    }

}
