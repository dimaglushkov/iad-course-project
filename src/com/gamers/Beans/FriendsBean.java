package com.gamers.Beans;


import com.gamers.DAO.FriendshipDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Friendship;
import com.gamers.Entities.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;

@Path("friends")
@Stateless
@Local(FriendsInterface.class)
public class FriendsBean implements FriendsInterface
{

    @Resource
    SessionContext sessionContext;

    private FriendshipDAO friendshipDAO = new FriendshipDAO();

    private PersonDAO personDAO = new PersonDAO();
    private Person currentPerson;

    @GET
    @Produces("application/json")
    @Path("/new/{nickname}")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject sendRequest(@PathParam("nickname")String nickname)
    {
        JSONObject response = new JSONObject();
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        Friendship test = friendshipDAO.findFriendshipByNicknames(nickname, currentPerson.getNickname());
        if (test != null)
        {
            response.put("success", "false");
            response.put("description", "This friendship already exists");
            return  response;
        }

        Person friend = personDAO.findByNickname(nickname);
        if (friend == null)
        {
            response.put("success", "false");
            response.put("description", "No user with such nickname");
            return  response;
        }

        if (nickname.equals(sessionContext.getCallerPrincipal().getName()))
        {
            response.put("success", "false");
            response.put("description", "go find some friends lmao");
            return  response;
        }

        Friendship friendReq = new Friendship();
        friendReq.setPerson(currentPerson);
        friendReq.setFriend(friend);
        friendReq.setConfirmed(false);

        friendshipDAO.create(friendReq);

        response.put("success", "true");
        response.put("description", "Friend request created");
        return response;
    }

    @POST
    @Path("/{nickname}/accept")
    @RolesAllowed({"admin", "user"})
    @Override
    public void acceptRequest(@PathParam("nickname") String nickname)
    {
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        if (nickname.equals(currentPerson.getNickname()))
            return;

        Friendship friendship = friendshipDAO.findFriendshipByNicknames(nickname, currentPerson.getNickname());
        if (friendship == null || friendship.isConfirmed())
            return;

        friendship.setConfirmed(true);

        friendshipDAO.acceptRequest(friendship);

    }

    @POST
    @Path("/{nickname}/decline")
    @RolesAllowed({"admin", "user"})
    @Override
    public void declineRequest(@PathParam("nickname") String nickname)
    {
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        if (nickname.equals(currentPerson.getNickname()))
            return;

        Friendship friendship = friendshipDAO.findFriendshipByNicknames(nickname, sessionContext.getCallerPrincipal().getName());
        if (friendship == null || friendship.isConfirmed())
            return;

        friendshipDAO.declineRequest(friendship);

    }


}
