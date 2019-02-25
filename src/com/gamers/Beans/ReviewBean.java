package com.gamers.Beans;

import com.gamers.DAO.GameDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.DAO.ReviewDAO;
import com.gamers.Entities.Game;
import com.gamers.Entities.Person;
import com.gamers.Entities.Review;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;


@Path("review")
@Stateless
@Local(ReviewInterface.class)
public class ReviewBean implements ReviewInterface
{

    @Resource
    SessionContext sessionContext;

    private JSONObject response;
    private PersonDAO personDAO = new PersonDAO();
    private ReviewDAO reviewDAO = new ReviewDAO();
    private GameDAO gameDAO = new GameDAO();
    private Person curPerson;

    @POST
    @Path("/new")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject create(@FormParam("gameId") String gameId, @FormParam("rate") String rate, @FormParam("description") String description)
    {
        response = new JSONObject();

        Review review = new Review();
        Long gameIdNumeric;

        try
        {
            gameIdNumeric = Long.valueOf(gameId);
        }
        catch (IllegalArgumentException e)
        {

            return initResponse(false, "Wrong id value");
        }
        Game game = gameDAO.findById(gameIdNumeric);

        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        review.setPerson(curPerson);
        review.setGame(game);
        review.setRate(Integer.valueOf(rate));
        review.setText(description);

        reviewDAO.create(review);

        return initResponse(true, "Review created");
    }

    @SuppressWarnings("Duplicates")
    @GET
    @Path("/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject getByNickname(@PathParam("nickname") String nickname)
    {
        JSONObject response = new JSONObject();

        JSONArray JsonArray = new JSONArray();
        List<Review> reviews = reviewDAO.findByNickname(nickname);
        return putReviewsToResponse(response, JsonArray, reviews);
    }

    @GET
    @Path("/game/{gameId}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject getByGameId(@PathParam("gameId") String gameId)
    {
         response = new JSONObject();

        JSONArray JsonArray = new JSONArray();
        List<Review> reviews = reviewDAO.findByGameId(Long.valueOf(gameId));
        return putReviewsToResponse(response, JsonArray, reviews);
    }

    @SuppressWarnings("Duplicates")
    private JSONObject putReviewsToResponse(JSONObject response, JSONArray jsonArray, List<Review> reviews)
    {
        for (Review review : reviews)
        {
            JSONObject obj = new JSONObject();
            obj.put("reviewid", review.getId());
            obj.put("gameid", review.getGame().getId());
            obj.put("gamename", review.getGame().getName());
            obj.put("gamerate", review.getGame().getName());
            jsonArray.add(obj);

        }

        response.put("review", jsonArray);
        return initResponse(true, "Review found");
    }

    private JSONObject initResponse(Boolean success, String desc)
    {
        response.put("success", success.toString());
        response.put("description", desc);
        return response;
    }
}
