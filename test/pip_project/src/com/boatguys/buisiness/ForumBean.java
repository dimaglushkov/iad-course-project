package com.boatguys.buisiness;

import com.boatguys.data.*;
import com.boatguys.entities.*;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.AccessLocalException;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.ws.rs.*;
import java.util.List;

@Path("forum")
@Stateless
@Local(Forum.class)
public class ForumBean implements Forum
{
    @Resource
    private SessionContext context;

    @Inject
    private NotificationSender sender;

    private Group adminGroup = new Group("administrators");
    private Group moderGroup = new Group("moderators");

    private UserDAO userDAO = new UserDAO();
    private ForumSectionDAO sectionDAO = new ForumSectionDAO();
    private ForumTopicDAO topicDAO = new ForumTopicDAO();
    private ForumRecordDAO recordDAO = new ForumRecordDAO();
    private CommentDAO commentDAO = new CommentDAO();

    private Gson gson =  new GsonBuilder().setExclusionStrategies(new ExclusionStrategy()
    {
        @Override
        public boolean shouldSkipField(FieldAttributes f)
        {
            return (f.getDeclaringClass() == User.class &&
                    (f.getName().equals("password") || f.getName().equals("boats")));
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass)
        {
            return false;
        }
    }).create();

    @GET
    @Produces("application/json")
    @Path("/sections/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getSectionList()
    {
        List<ForumSection> sections = sectionDAO.getAllSections();

        return gson.toJson(sections);
    }

    @GET
    @Produces("application/json")
    @Path("/{section}/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getTopicsInSection(@PathParam("section") String sectionName)
    {
        ForumSection section = sectionDAO.getByID(sectionName);
        List<ForumTopic> topics = topicDAO.getAllTopicsInSection(section);

        return gson.toJson(topics);
    }

    @GET
    @Produces("application/json")
    @Path("/{section}/{topic}/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getRecordsInTopic(@PathParam("section") String sectionName,@PathParam("topic") String topicName)
    {
        ForumSection section = sectionDAO.getByID(sectionName);
        ForumTopicKey topicKey = new ForumTopicKey(section, topicName);
        ForumTopic topic = topicDAO.getByID(topicKey);

        List<ForumRecord> records = recordDAO.getAllForTopic(topic);

        return gson.toJson(records);
    }

    @GET
    @Produces("application/json")
    @Path("/records/{recordId}/all")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String getCommentsToRecord(@PathParam("recordId")long recordId)
    {
        ForumRecord record = recordDAO.getByID(recordId);
        List<Comment> comments = commentDAO.getAllForForumRecord(record);

        return gson.toJson(comments);
    }

    @POST
    @Path("/sections/delete/{section}")
    @RolesAllowed({"administrators"})
    @Override
    public void deleteSection(@PathParam("section") String sectionName)
    {
        ForumSection section = sectionDAO.getByID(sectionName);
        sectionDAO.delete(section);
    }

    @POST
    @Path("/sections/new/{section}")
    @RolesAllowed({"administrators"})
    @Override
    public void createSection(@PathParam("section") String sectionName)
    {
        ForumSection section = new ForumSection(sectionName);
        sectionDAO.save(section);

    }

    @POST
    @Path("/sections/rename/{oldName}/{newName}")
    @RolesAllowed({"administrators"})
    @Override
    public void renameSection(@PathParam("oldName") String oldSectionName,@PathParam("newName") String newSectionName)
    {
        ForumSection section = sectionDAO.getByID(oldSectionName);
        section.setSectionName(newSectionName);

        sectionDAO.update(section);
    }

    @POST
    @Path("/{section}/delete/{topic}")
    @RolesAllowed({"administrators", "moderators"})
    @Override
    public void deleteTopic(@PathParam("section") String sectionName,@PathParam("topic") String topicName)
    {
        ForumSection section = sectionDAO.getByID(sectionName);
        ForumTopicKey topicKey = new ForumTopicKey(section, topicName);
        ForumTopic topic = topicDAO.getByID(topicKey);

        topicDAO.delete(topic);
    }

    @POST
    @Path("/{section}/create/{topic}")
    @RolesAllowed({"administrators", "moderators"})
    @Override
    public void createTopic(@PathParam("section") String sectionName,@PathParam("topic") String topicName)
    {
        ForumSection section = sectionDAO.getByID(sectionName);
        ForumTopic topic = new ForumTopic(section,topicName);

        topicDAO.save(topic);
    }

    @POST
    @Path("/{section}/rename/{oldTopicName}/{newTopicName}")
    @RolesAllowed({"administrators", "moderators"})
    @Override
    public void renameTopic(@PathParam("section") String sectionName,@PathParam("oldTopicName") String oldTopicName,@PathParam("newTopicName") String newTopicName)
    {
        ForumSection section = sectionDAO.getByID(sectionName);
        ForumTopicKey topicKey = new ForumTopicKey(section, oldTopicName);
        ForumTopic topic = topicDAO.getByID(topicKey);

        topic.setTopicName(newTopicName);
        topicDAO.update(topic);

    }

    @POST
    @Path("/records/delete/{recordId}")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void deleteRecord(@PathParam("recordId") long recordId)
    {
        ForumRecord record = recordDAO.getByID(recordId);
        User user = userDAO.getByEmail(context.getCallerPrincipal().getName());

        if(isAuthorOrAdminOrModer(user,record))
            recordDAO.delete(record);
        else
            throw new AccessLocalException("Dont't have permition to delete the record");

    }

    @POST
    @Path("/{section}/{topic}/new")
    @Produces("application/json")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String addRecord(@PathParam("section") String sectionName, @PathParam("topic") String topicName, @FormDataParam("message") String message)
    {
        ForumSection section = sectionDAO.getByID(sectionName);
        ForumTopicKey topicKey = new ForumTopicKey(section, topicName);
        ForumTopic topic = topicDAO.getByID(topicKey);
        User user = userDAO.getByEmail(context.getCallerPrincipal().getName());

        ForumRecord record = new ForumRecord(topic,message, user);

        recordDAO.save(record);

        try
        {
            sender.sendMessage("В разделе "+sectionName+", в теме "+topicName+" опубликована новая запись");
        } catch (NamingException e)
        {
            e.printStackTrace();
        } catch (JMSException e)
        {
            e.printStackTrace();
        }

        return gson.toJson(record);
    }

    @POST
    @Path("/records/change/{recordId}")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void changeRecord(@PathParam("recordId") long recordId,@FormDataParam("newMessage") String newMessageText)
    {
        ForumRecord record = recordDAO.getByID(recordId);
        User user = userDAO.getByEmail(context.getCallerPrincipal().getName());

        if(isAuthorOrAdminOrModer(user, record))
        {
            record.setMessage(newMessageText);
            recordDAO.update(record);
        }
        else
            throw new AccessLocalException("Dont't have permition to change the record");
    }

    @POST
    @Path("/records/delete/comment/{commentId}")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void deleteComment(@PathParam("commentId") long commentId)
    {
        Comment comment = commentDAO.getByID(commentId);
        User user = userDAO.getByEmail(context.getCallerPrincipal().getName());

        if(isAuthorOrAdminOrModer(user, comment))
            commentDAO.delete(comment);
        else
            throw new AccessLocalException("Don't have permission to delete the comment");
    }

    @POST
    @Path("/records/{recordId}/new")
    @Produces("application/json")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public String addComment(@PathParam("recordId") long recordId, @FormDataParam("commentText") String commentText)
    {
        ForumRecord record = recordDAO.getByID(recordId);
        User user = userDAO.getByEmail(context.getCallerPrincipal().getName());
        Comment comment = new Comment(record,commentText, user);

        commentDAO.save(comment);

        try
        {
            sender.sendMessage("На форуме появился новый комментарий!");
        } catch (NamingException e)
        {
            e.printStackTrace();
        } catch (JMSException e)
        {
            e.printStackTrace();
        }

        return gson.toJson(comment);
    }

    @POST
    @Path("/records/change/comment/{commentId}")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public void changeComment ( @PathParam("commentId") long commentId, @FormDataParam("newCommentText") String newCommentText)
    {
        Comment comment = commentDAO.getByID(commentId);
        User user = userDAO.getByEmail(context.getCallerPrincipal().getName());

        if(isAuthorOrAdminOrModer(user, comment))
        {
            comment.setMessage(newCommentText);
            commentDAO.update(comment);
        }
        else
            throw new AccessLocalException("Dont't have permition to change the comment");
    }

    @POST
    @Path("/sections/merge/{source}/{destination}")
    @RolesAllowed({"administrators"})
    @Override
    public void mergeSections(@PathParam("source") String sourceSectionName,@PathParam("destination") String destSectionName)
    {
        ForumSection sourceSection = sectionDAO.getByID(sourceSectionName);
        ForumSection destSection = sectionDAO.getByID(destSectionName);

        List<ForumTopic> topicsFromSourceSection = topicDAO.getAllTopicsInSection(sourceSection);

        for(ForumTopic t : topicsFromSourceSection)
        {
            t.setSection(destSection);
            topicDAO.update(t);
        }

        sectionDAO.delete(sourceSection);

    }

    @POST
    @Path("/sections/merge/{sourceSection}/{sourceTopic}/to/{destSection}/{destTopic}")
    @RolesAllowed({"administrators", "moderators"})
    @Override
    public void mergeTopics(@PathParam("sourceSection") String sourceSectionName,
                     @PathParam("sourceTopic") String sourceTopicName,
                     @PathParam("destSection") String destSectionName,
                     @PathParam("destTopic") String destTopicName)
    {
        ForumSection sourceSection = sectionDAO.getByID(sourceSectionName);
        ForumSection destSection = sectionDAO.getByID(destSectionName);

        ForumTopicKey sourceTopicKey = new ForumTopicKey(sourceSection, sourceTopicName);
        ForumTopicKey destTopicKey = new ForumTopicKey(destSection, destTopicName);

        ForumTopic sourceTopic = topicDAO.getByID(sourceTopicKey);
        ForumTopic destTopic = topicDAO.getByID(destTopicKey);

        List<ForumRecord> recordsFromSourceTopic = recordDAO.getAllForTopic(sourceTopic);

        for(ForumRecord r : recordsFromSourceTopic)
        {
            r.setTopic(destTopic);
            recordDAO.update(r);
        }

    }

    private boolean isAuthorOrAdminOrModer(User user, Comment comment)
    {
        return comment.getAuthor().equals(user)
                || user.getGroups().contains(moderGroup)
                || user.getGroups().contains(adminGroup);
    }

    private boolean isAuthorOrAdminOrModer(User user, ForumRecord record)
    {
        return record.getAuthor().equals(user)
                || user.getGroups().contains(moderGroup)
                || user.getGroups().contains(adminGroup);
    }
}
