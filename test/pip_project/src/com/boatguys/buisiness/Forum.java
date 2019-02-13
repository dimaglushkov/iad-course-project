package com.boatguys.buisiness;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;

@Local
public interface Forum
{

    @RolesAllowed({"users", "administrators", "moderators"})
    String getSectionList();


    @RolesAllowed({"users", "administrators", "moderators"})
    String getTopicsInSection(String section);

    @RolesAllowed({"users", "administrators", "moderators"})
    String getRecordsInTopic(String section, String topic);

    @RolesAllowed({"users", "administrators", "moderators"})
    String getCommentsToRecord(long recordId);


    @RolesAllowed({"administrators"})
    void deleteSection(String section);

    @RolesAllowed({"administrators"})
    void createSection(String sectionName);

    @RolesAllowed({"administrators"})
    void renameSection(String oldSectionName, String newSectionName);

    @RolesAllowed({"administrators", "moderators"})
    void deleteTopic(String section, String topic);

    @RolesAllowed({"administrators", "moderators"})
    void createTopic(String section, String topicName);

    @RolesAllowed({"administrators", "moderators"})
    void renameTopic(String section, String oldTopicName, String newTopicName);

    @RolesAllowed({"users", "administrators", "moderators"})
    void deleteRecord(long recordId);

    @RolesAllowed({"users", "administrators", "moderators"})
    String addRecord(String section, String topic, String message);

    @RolesAllowed({"users", "administrators", "moderators"})
    void changeRecord(long recordId, String newMessageText);

    @RolesAllowed({"users", "administrators", "moderators"})
    void deleteComment(long commentId);

    @RolesAllowed({"users", "administrators", "moderators"})
    String addComment(long recordId, String commentText);

    @RolesAllowed({"users", "administrators", "moderators"})
    void changeComment (long commentId, String newCommentText);

    @RolesAllowed({"administrators"})
    void mergeSections(String sourceSection, String destSection);


    @RolesAllowed({"administrators", "moderators"})
    void mergeTopics(String sourceSection,
                     String sourceTopic,
                     String destSection,
                     String destTopic);

}
