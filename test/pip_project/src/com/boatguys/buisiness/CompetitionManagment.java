package com.boatguys.buisiness;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;


@Local
public interface CompetitionManagment
{
    @RolesAllowed({"administrators", "moderators"})
    void addCompetitionResults(String resultsMapInJson, long competitionId);

    @RolesAllowed({"administrators", "moderators"})
    void addCompetition(String level, String type, long trackId);

    @RolesAllowed({"administrators", "moderators"})
    void addTrack(String name,
                  int distnace,
                  int complexity,
                  String location,
                  String type);

    @RolesAllowed({"users", "administrators", "moderators"})
    String getCompetitions();

    @RolesAllowed({"users","administrators", "moderators"})
    String getCompetitionResult(long competitionId);
}
