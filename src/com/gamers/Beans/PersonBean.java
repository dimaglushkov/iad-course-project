package com.gamers.Beans;

import com.gamers.DAO.*;
import com.gamers.Entities.Game;
import com.gamers.Entities.Group;
import com.gamers.Entities.Person;
import com.gamers.Entities.Review;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

@Path("user")
@Stateless
@Local(PersonInterface.class)
public class PersonBean implements PersonInterface, Serializable
{

    @Resource
    SessionContext sessionContext;

    private PersonDAO personDAO = new PersonDAO();

    @POST
    @Produces("application/json")
    @Path("/new")
    @Override
    public JSONObject register(@FormParam("nickname") String nickname,
                             @FormParam("password") String password,
                             @FormParam("email") String email)
    {

        JSONObject responseJSON = new JSONObject();
        Person person = new Person();
        Group group = new Group("user");

        if (isPersonWithSuchNicknameOrEmailExists(nickname, email))
                return responseOnFail("User with such nickname or email already exists.");

        try
        {
            person.setEmail(email);
            person.setNickname(nickname);
            person.setPassword(SHA256(password));
            person.addGroup(group);
            personDAO.create(person);
        }
        catch (Exception e)
        {
            return responseOnFail(e.getMessage());
        }
        responseJSON.put("success", "true");
        responseJSON.put("description", "Profile created.");
        return responseJSON;
    }

    @POST
    @Path("/logout")
    @RolesAllowed({"user", "admin", "banned-user"})
    public void logout(@Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException
    {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    @GET
    @Path("/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"user", "admin"})
    public JSONObject account(@PathParam("nickname") String nickname)
    {
        JSONObject response = new JSONObject();

        FriendshipDAO friendshipDAO = new FriendshipDAO();
        GameDAO gameDAO = new GameDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        Person person = personDAO.findByNickname(nickname);
        if (person == null)
            return responseOnFail("This user doesn't exist");

        response.put("success", "true");
        response.put("description", "Profile exists");
        response.put("nickname", nickname);

        JSONArray JsonArray1 = new JSONArray();


        List<Person> friends = friendshipDAO.findFriendsByNickname(nickname);
        for (Person friend : friends)
        {
            JSONObject obj = new JSONObject();
            obj.put("friendname", friend.getNickname());
            JsonArray1.add(obj);
        }
        response.put("friends", JsonArray1);

        JSONArray JsonArray2 = new JSONArray();
        List<Game> games = gameDAO.findGamesByNickname(nickname);
        for (Game game: games)
        {
            JSONObject obj = new JSONObject();
            obj.put("gameid", game.getId());
            obj.put("gamename", game.getName());
            JsonArray2.add(obj);
        }
        response.put("games", JsonArray2);

        JSONArray JsonArray4 = new JSONArray();
        List<Review> reviews = reviewDAO.findByNickname(nickname);
        for (Review review: reviews)
        {
            JSONObject obj = new JSONObject();
            obj.put("reviewid", review.getId());
            obj.put("gameid", review.getGame().getId());
            obj.put("gamename", review.getGame().getName());
            obj.put("gamerate", review.getGame().getName());
            JsonArray4.add(obj);

        }
        response.put("review", JsonArray4);


        return response;
    }



    private JSONObject responseOnFail(String description)
    {
        JSONObject resp = new JSONObject();
        resp.put("success", "false");
        resp.put("description", description);
        return resp;
    }

    private String SHA256(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(no.toString(16));

            while (hashtext.length() < 32)
            {
                hashtext.insert(0, "0");
            }

            return hashtext.toString();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private boolean isPersonWithSuchNicknameOrEmailExists(String nickname, String email)
    {
        Person person = personDAO.findByNickname(nickname);
        if (person != null)
            return true;

        person = personDAO.findByEmail(email);
        if (person != null)
            return true;

        return false;

    }



}
