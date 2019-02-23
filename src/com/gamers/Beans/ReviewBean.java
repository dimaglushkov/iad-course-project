package com.gamers.Beans;

import com.gamers.DAO.GameDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.DAO.ReviewDAO;
import com.gamers.Entities.Game;
import com.gamers.Entities.Person;
import com.gamers.Entities.Review;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;


@Path("review")
@Stateless
@Local(ReviewInterface.class)
public class ReviewBean implements ReviewInterface
{

    @Resource
    SessionContext sessionContext;

    PersonDAO personDAO = new PersonDAO();
    Person curPerson;

    @POST
    @Path("/new")
    @RolesAllowed({"admin", "user"})
    @Override
    public void create(@FormParam("gameId") String gameId, @FormParam("rate") String rate, @FormParam("description") String description)
    {
        ReviewDAO reviewDAO = new ReviewDAO();
        Review review = new Review();
        GameDAO gameDAO = new GameDAO();
        Game game = gameDAO.findById(Long.valueOf(gameId));

        curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        review.setPerson(curPerson);
        review.setGame(game);
        review.setRate(Integer.valueOf(rate));
        review.setText(description);

        reviewDAO.create(review);

    }

    @GET
    @Path("/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject getByNickname(@PathParam("nickname") String nickname)
    {
        JSONObject response = new JSONObject();
        

        return response;
    }

    @GET
    @Path("/{gameId}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject getByGameId(@PathParam("gameId") String gameId)
    {
        return null;
    }
}
