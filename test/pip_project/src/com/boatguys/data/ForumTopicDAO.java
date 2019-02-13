package com.boatguys.data;

import com.boatguys.entities.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ForumTopicDAO
{
    private DBOperations dbOperations;

    public ForumTopicDAO()
    {
        dbOperations = new DBOperations();
    }

    public void save(ForumTopic forumTopic)
    {
        dbOperations.createEntity(forumTopic);
    }

    public void update(ForumTopic forumTopic)
    {
        if(forumTopic.getOldKey() == null)
            return;

        if(!(forumTopic.getOldKey().getSection().equals(forumTopic.getId().getSection())))
            dbOperations.nativeQueryUpdates("update ТЕМЫ_ФОРУМА set РАЗДЕЛ = \'"+forumTopic.getId().getSection()+
                    "\' where РАЗДЕЛ =  \'"+forumTopic.getOldKey().getSection()+"\' and ТЕМА = \'" + forumTopic.getOldKey().getTopicName()+"\'");

        if(!(forumTopic.getOldKey().getTopicName().equals(forumTopic.getId().getTopicName())))
            dbOperations.nativeQueryUpdates("update ТЕМЫ_ФОРУМА set ТЕМА = \'"+forumTopic.getId().getTopicName()+
                    "\' where РАЗДЕЛ =  \'"+forumTopic.getId().getSection()+"\' and ТЕМА = \'" + forumTopic.getOldKey().getTopicName()+"\'");
    }

    public void delete(ForumTopic forumTopic)
    {
        dbOperations.deleteEntity(forumTopic);
    }

    public ForumTopic getByID(ForumTopicKey id) throws EntityNotFoundException
    {
        return (ForumTopic) dbOperations.findEntityByObjectId(ForumTopic.class, id);
    }

    public List<ForumTopic> getAllTopicsInSection(ForumSection section)
    {
        return dbOperations.nativeQuery("select * from ТЕМЫ_ФОРУМА where РАЗДЕЛ = '"+section.getSectionName()+"'", ForumTopic.class);
    }
}
