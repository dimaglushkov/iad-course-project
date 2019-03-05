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

@Path("friend")
@Stateless
@Local(FriendsInterface.class)
public class FriendsBean implements FriendsInterface
{

    @Resource
    SessionContext sessionContext;

    private JSONObject response;

    private FriendshipDAO friendshipDAO = new FriendshipDAO();

    private PersonDAO personDAO = new PersonDAO();
    private Person currentPerson;

    @GET
    @Produces("application/json")
    @Path("/new/{nickname}")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject addFriend(@PathParam("nickname")String nickname)
    {
        response = new JSONObject();
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        Friendship test = friendshipDAO.findFriendshipByNicknames(nickname, currentPerson.getNickname());
        if (test != null)
        {
            return initResponse(false, "This friendship already exists");
        }

        Person friend = personDAO.findByNickname(nickname);
        if (friend == null)
        {
            return initResponse(false, "Wrong nickname");
        }

        if (nickname.equals(sessionContext.getCallerPrincipal().getName()))
        {
            return initResponse(false, "go find some friends lmao");
        }

        Friendship friendReq = new Friendship();
        friendReq.setPerson(currentPerson);
        friendReq.setFriend(friend);
        friendReq.setConfirmed(false);

        friendshipDAO.create(friendReq);

        return initResponse(true, "Friend request created");
    }

    @POST
    @Path("/accept/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject acceptRequest(@PathParam("nickname") String nickname)
    {
        response = new JSONObject();
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        if (nickname.equals(currentPerson.getNickname()))
            return initResponse(false, "dude you CANNOT accept yourself as a friend here");

        Friendship friendship = friendshipDAO.findFriendshipByNicknames(nickname, currentPerson.getNickname());
        if (friendship == null || friendship.isConfirmed())
            return initResponse(false, "This request already confirmed or (what's more possible) not exists. Go outside");

        friendship.setConfirmed(true);

        friendshipDAO.acceptRequest(friendship);

        return initResponse(true, "Request accepted");

    }

    @POST
    @Path("/decline/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject declineRequest(@PathParam("nickname") String nickname)
    {
        response = new JSONObject();
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        if (nickname.equals(currentPerson.getNickname()))
            return initResponse(false, "You CANNOT decline request from YOURSELF");

        Friendship friendship = friendshipDAO.findFriendshipByNicknames(nickname, sessionContext.getCallerPrincipal().getName());
        if (friendship == null || friendship.isConfirmed())
            return initResponse(false, "This request already confirmed or (what's more possible) not exists. Go outside");

        friendshipDAO.declineRequest(friendship);
        return initResponse(true, "Request declined");

    }

    @POST
    @Path("/delete/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject removeFriend(@PathParam("nickname") String nickname)
    {
        response = new JSONObject();
        Friendship friendship = friendshipDAO.findFriendshipByNicknames(nickname, sessionContext.getCallerPrincipal().getName());
        if (friendship == null)
            return initResponse(false, "YEah you can NOT break friendship if theres none");

        friendshipDAO.delete(friendship);
        return initResponse(true, "Sadly, you are no longer friends");
    }

    @GET
    @Path("/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject getFriends(@PathParam("nickname") String nickname)
    {
        response = new JSONObject();
        FriendshipDAO friendshipDAO = new FriendshipDAO();

        Person person = personDAO.findByNickname(nickname);

        JSONArray jsonArray = new JSONArray();

        List<Person> friends = friendshipDAO.findFriendsByNickname(nickname);
        for (Person friend : friends)
        {
            JSONObject obj = new JSONObject();
            obj.put("friendname", friend.getNickname());
            jsonArray.add(obj);
        }
        response.put("friends", jsonArray);

        return initResponse(true, "Friendlist found");
    }

    private JSONObject initResponse(boolean success, String desc)
    {
        response.put("success", success);
        response.put("description", desc);
        return response;
    }

}
