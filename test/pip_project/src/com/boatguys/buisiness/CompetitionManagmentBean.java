package com.boatguys.buisiness;

import com.boatguys.data.CompetitionDAO;
import com.boatguys.data.CompetitionResultDAO;
import com.boatguys.data.TrackDAO;
import com.boatguys.data.UserDAO;
import com.boatguys.entities.Competition;
import com.boatguys.entities.CompetitionResult;
import com.boatguys.entities.Track;
import com.boatguys.entities.User;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Path("competitions")
@Stateless
@Local(CompetitionManagment.class)
public class CompetitionManagmentBean implements CompetitionManagment
{
    @Resource
    private SessionContext context;

    @Inject
    private NotificationSender sender;

    private CompetitionDAO competitionDAO = new CompetitionDAO();
    private CompetitionResultDAO competitionResultDAO = new CompetitionResultDAO();
    private UserDAO userDAO = new UserDAO();
    private TrackDAO trackDAO = new TrackDAO();

    private Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy()
    {
        @Override
        public boolean shouldSkipField(FieldAttributes f)
        {
            return (f.getDeclaringClass() == User.class &&
                    ( f.getName().equals("password") || f.getName().equals("boats")));
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass)
        {
            return false;
        }
    }).create();

    private UserTransaction transaction = context.getUserTransaction();

    @POST
    @Path("/newCompResults")
    @RolesAllowed({"administrators", "moderators"})
    @Override
    public void addCompetitionResults(@FormParam("results") String resultsMapInJson, @FormParam("competitionId") long competitionId)
    {
        Type type = new TypeToken<Map<String, Integer>>(){}.getType();

        Map<String, Integer> resultsMap = gson.fromJson(resultsMapInJson, type);

        for(String username : resultsMap.keySet())
        {
            User user = userDAO.getByEmail(username);
            int rate = resultsMap.get(username);

            Competition competition = competitionDAO.getByID(competitionId);

            try
            {
                transaction.begin();

                int ratePoints = (11 - rate) * 10 * competition.getTrack().getComplexity();
                user.addToRate(ratePoints);

                CompetitionResult competitionResult = new CompetitionResult(user, competition, rate);

                userDAO.update(user);
                competitionResultDAO.save(competitionResult);

                transaction.commit();
            }
            catch (Exception e)
            {
                try {
                    transaction.rollback();
                } catch (SystemException syex) {
                    throw new EJBException
                            ("Rollback failed: " + syex.getMessage());
                }
                throw new EJBException
                        ("Transaction failed: " + e.getMessage());
            }
        }

        try
        {
            sender.sendMessage("New compettion results");
        } catch (NamingException e)
        {
            e.printStackTrace();
        } catch (JMSException e)
        {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/newCompetition")
    @RolesAllowed({"administrators", "moderators"})
    @Override
    public void addCompetition(@FormParam("level") String level,@FormParam("type") String type, @FormParam("trackId") long trackId)
    {
        Track track = trackDAO.getByID(trackId);

        Competition newCompetition = new Competition(level, type, track);

        competitionDAO.save(newCompetition);
    }

    @POST
    @Path("/newTrack")
    @RolesAllowed({"administrators", "moderators"})
    @Override
    public void addTrack(@FormParam("name") String name,
                  @FormParam("distance") int distnace,
                  @FormParam("complexity") int complexity,
                  @FormParam("location") String location,
                  @FormParam("type") String type)
    {
        Track newTrack = new Track(name, distnace, complexity, location, type);

        trackDAO.save(newTrack);

        try
        {
            sender.sendMessage("New track was added");
        } catch (NamingException e)
        {
            e.printStackTrace();
        } catch (JMSException e)
        {
            e.printStackTrace();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getCompetitions()
    {
        List<Competition> competitions = competitionDAO.getAll();

        return gson.toJson(competitions);
    }

    @GET
    @Produces("application/json")
    @Path("/{competitionId}")
    @RolesAllowed({"users","administrators", "moderators"})
    @Override
    public String getCompetitionResult(@PathParam("competitionId") long competitionId)
    {
        Competition competition = competitionDAO.getByID(competitionId);

        List<CompetitionResult> results = competitionResultDAO.getAllForTheCompetition(competition);

        return gson.toJson(results);
    }
}
