package com.gamers.Beans;


import com.gamers.DAO.GameDAO;
import com.gamers.DAO.LibraryDAO;
import com.gamers.DAO.PersonDAO;
import com.gamers.Entities.Game;
import com.gamers.Entities.Library;
import com.gamers.Entities.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.container.Suspended;
import java.sql.Date;
import java.util.List;

@Path("game")
@Stateless
@Local(GameInterface.class)
public class GameBean implements GameInterface
{

    @Resource
    SessionContext sessionContext;

    private GameDAO gameDAO = new GameDAO();
    private JSONObject response;

    @POST
    @Path("/new")
    @RolesAllowed("admin")
    @Produces("application/json")
    @Override
    public JSONObject createGame(@FormParam("name") String name,
                                 @FormParam("date") String dateStr,
                                 @FormParam("description") String description)
    {
        response = new JSONObject();

        Date date;
        try
        {
            date = Date.valueOf(dateStr);
        }
        catch (IllegalArgumentException e)
        {
            return initResponse(false, "Wrong date format");
        }
        Game game = new Game();
        game.setDate(date);
        game.setName(name);
        game.setDesc(description);

        gameDAO.create(game);
        return initResponse(true, "Game greated");

    }


    @GET
    @Path("delete/{id}")
    @Produces("application/json")
    @RolesAllowed("admin")
    @Override
    public JSONObject deleteGame(@PathParam("id") long id)
    {
        response = new JSONObject();
        Game game =gameDAO.findById(id);
        if (game == null)
            return initResponse(false, "Game not found");

        LibraryDAO libraryDAO = new LibraryDAO();
        libraryDAO.deleteGameFromLibraries(game);
        gameDAO.delete(game);
        return initResponse(true, "Game deleted");
    }


    @GET
    @Path("/add/{gameid}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject addToLib(@PathParam("gameid") long id)
    {
        response = new JSONObject();
        PersonDAO personDAO = new PersonDAO();
        Person curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        LibraryDAO libraryDAO = new LibraryDAO();

        Game game = gameDAO.findById(id);

        Library library = libraryDAO.findByGameAndPerson(game, curPerson);
        if (library != null)
            return initResponse(false, "You already owned this game");

        library = new Library();
        library.setGame(game);
        library.setPerson(curPerson);

        if (!libraryDAO.create(library))
            return initResponse(false, "Error while adding this game to your library");

        return initResponse(true, "Game added to your library");
    }

    @GET
    @Path("/remove/{gameid}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject removeFormLib(@PathParam("gameid") long id)
    {
        response = new JSONObject();

        PersonDAO personDAO = new PersonDAO();
        LibraryDAO libraryDAO = new LibraryDAO();

        Person curPerson = personDAO.findByNickname(sessionContext.getCallerPrincipal().getName());
        Game game = gameDAO.findById(id);

        Library library = libraryDAO.findByGameAndPerson(game, curPerson);
        if (library == null)
            return initResponse(false, "This game is not in your librarty");
        libraryDAO.delete(library);

        return initResponse(true, "Game removed from your library");
    }


    @GET
    @Path("/user/{nickname}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject getUsersGames(@PathParam("nickname") String nickname)
    {

        List<Game> games = gameDAO.findGamesByNickname(nickname);

        response = new JSONObject();
        initResponse(true, "Library found");

        JSONArray JsonArray = new JSONArray();
        for (Game game: games)
        {
            JSONObject obj = new JSONObject();
            obj.put("gameid", game.getId());
            obj.put("gamename", game.getName());
            JsonArray.add(obj);
        }
        response.put("games", JsonArray);
        return response;
    }

    @GET
    @Path("/{gameId}")
    @Produces("application/json")
    @RolesAllowed({"admin", "user"})
    @Override
    public JSONObject getGameInfo(@PathParam("gameId") long gameId)
    {
        response = new JSONObject();

        Game game = gameDAO.findById(gameId);

        if (game == null)
            return initResponse(false, "Game with such id not found");

        JSONObject gameObj = new JSONObject();

        gameObj.put("gameName", game.getName());
        gameObj.put("gameId", gameId);
        gameObj.put("gameDescription", game.getDesc());
        gameObj.put("gameDate", game.getDate().toString());
        response.put("game", gameObj);

        return initResponse(true, "Game found");
    }

    @GET
    @PathParam("/{gameName}")
    @Produces("application/json")



    private JSONObject initResponse(Boolean success, String desc)
    {
        response.put("success", success.toString());
        response.put("description", desc);
        return response;
    }

}
