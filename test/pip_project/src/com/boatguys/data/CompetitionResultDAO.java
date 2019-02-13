package com.boatguys.data;

import com.boatguys.entities.Competition;
import com.boatguys.entities.CompetitionResult;
import com.boatguys.entities.User;

import java.util.List;

public class CompetitionResultDAO
{
    private DBOperations dbOperations;

    public CompetitionResultDAO()
    {
        dbOperations = new DBOperations();
    }

    public void save(CompetitionResult competitionResults)
    {
        dbOperations.createEntity(competitionResults);
    }

    public void update(CompetitionResult competitionResults)
    {
        dbOperations.updateEntity(competitionResults);
    }

    public void delete(CompetitionResult competitionResults)
    {
        dbOperations.deleteEntity(competitionResults);
    }

    public CompetitionResult getByID(long id)
    {
        return (CompetitionResult) dbOperations.findEntityById(CompetitionResult.class, id);
    }


    public List<CompetitionResult> getAllForTheUser(User user)
    {
        return dbOperations.queryWithCondition(CompetitionResult.class, User.class, user, "user");
    }

    public List<CompetitionResult> getAllForTheCompetition(Competition competition)
    {
        return dbOperations.queryWithCondition(CompetitionResult.class, Competition.class, competition, "competition");
    }

}
