package com.gamers.Beans;

import com.gamers.DAO.*;
import com.gamers.Entities.*;
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

    private JSONObject response;

    @POST
    @Produces("application/json")
    @Path("/new")
    @Override
    public JSONObject register(@FormParam("nickname") String nickname,
                             @FormParam("password") String password,
                             @FormParam("email") String email)
    {

        response = new JSONObject();
        Person person = new Person();
        Group group = new Group("user");

        if (isPersonWithSuchNicknameOrEmailExists(nickname, email))
                return initResponse(false,"User with such nickname or email already exists.");

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
            return initResponse(false, e.getMessage());
        }
        return initResponse(true, "Profile created");
    }

    @POST
    @Path("/logout")
    @RolesAllowed({"user", "admin", "banned-user"})
    public void logout(@Context HttpServletResponse response,
                       @Context HttpServletRequest request) throws IOException
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
        response = new JSONObject();
        Person person = personDAO.findByNickname(nickname);
        if (person == null)
            return initResponse(false, "This user doesn't exist");
        InfoDAO infoDAO = new InfoDAO();
        Info info = infoDAO.findByPersonId(person.getId());

        FriendshipDAO friendshipDAO = new FriendshipDAO();
        GameDAO gameDAO = new GameDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        int numOfFriends = friendshipDAO.findFriendsByNickname(nickname).size();
        int numOfGames = gameDAO.findGamesByNickname(nickname).size();
        int numOfReviews  = reviewDAO.findByNickname(nickname).size();

        if (info.getName()!=null)
            response.put("name", info.getName());

        if (info.getSurname()!=null)
            response.put("surname", info.getSurname());

        if (info.getCountry()!=null)
            response.put("country", info.getCountry());

        if (info.getCity()!=null)
            response.put("city", info.getCity());

        if (info.getBirthDate()!=null)
            response.put("birthDate", info.getBirthDate().toString().substring(0,10));

        if (info.getRegisterDate()!=null)
            response.put("registerDate", info.getRegisterDate().toString().substring(0,10));


        response.put("numOfFriends", numOfFriends);
        response.put("numOfGames", numOfGames);
        response.put("numOfReviews", numOfReviews);
        response.put("email", person.getEmail());
        response.put("id", person.getId());

        return initResponse(true, "Profile found");
    }



    private JSONObject initResponse(boolean success, String description)
    {
        response.put("success", success);
        response.put("description", description);
        return response;
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
