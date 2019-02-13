package com.boatguys.buisiness;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;

@Local
public interface ProducersInfo
{

    @RolesAllowed({"users", "administrators", "moderators"})
    String getAllProducers();


    @RolesAllowed({"users", "administrators", "moderators"})
    String getDetailsFromProducer(long producerId);


    @RolesAllowed({"users", "administrators", "moderators"})
    String getAllBaseBoats();


    @RolesAllowed({"producers"})
    void addDetail(String type,
                   int weight,
                   int speedEffect,
                   int controllabilityEffect,
                   int boostEffect,
                   int price);


    @RolesAllowed({"administrators", "producers"})
    void removeDetail(long detailId);

    @RolesAllowed({"producers"})
    void addNewProducer(String name,
                        String phoneNumber,
                        String webSite);
}
