package com.gamers.Beans;


import com.gamers.DAO.FriendshipDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Friendship;
import com.gamers.Entities.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    FriendshipDAO friendshipDAO = new FriendshipDAO();

    PersonDAO personDAO = new PersonDAO();
    Person currentPerson;


    @POST
    @Path("/new/{nickname}")
    @Override
    public JSONObject sendRequest(@PathParam("nickname")String nickname)
    {
        JSONObject response = new JSONObject();
        currentPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        Friendship test = friendshipDAO.findFriendshipByNicknames(nickname, currentPerson.getNickname());
        if (test != null)
        {
            response.put("success", "false");
            response.put("description", "No user with such nickname");
            return  response;
        }

        Person friend = personDAO.findByNickname(nickname);
        if (friend == null)
        {
            response.put("success", "false");
            response.put("description", "This friendship already exists");
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

    @GET
    @Path("/{nickname}/accept")
    @Override
    public void acceptRequest(@PathParam("nickname") String nickname)
    {
        Friendship friendship = friendshipDAO.findFriendshipByNicknames(nickname, sessionContext.getCallerPrincipal().getName());
        if (friendship == null || friendship.isConfirmed())
            return;

        friendship.setConfirmed(true);

        friendshipDAO.update(friendship);

    }

    @GET
    @Path("/{nickname}/accept")
    @Override
    public void declineRequest(@PathParam("nickname") String nickname)
    {
        Friendship friendship = friendshipDAO.findFriendshipByNicknames(nickname, sessionContext.getCallerPrincipal().getName());
        if (friendship == null || friendship.isConfirmed())
            return;

        friendshipDAO.delete(friendship);

    }

    @GET
    @Path("/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject usersFriends(@PathParam("nickname") String nickname)
    {
        JSONObject response = new JSONObject();
        Person person = personDAO.findByNickname(nickname);

        response.put("success", "true");
        response.put("description", "List of friends created");

        JSONArray jsonArray = new JSONArray();

        List<Person> friends = friendshipDAO.findFriendsByNickname(nickname);
        for (Person friend : friends)
        {
            JSONObject obj = new JSONObject();
            obj.put("friendname", friend.getNickname());
            jsonArray.add(obj);
        }
        response.put("friends", jsonArray);

        return response;
    }
}
