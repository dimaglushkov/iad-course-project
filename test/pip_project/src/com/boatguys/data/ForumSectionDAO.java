package com.boatguys.data;

import com.boatguys.entities.ForumSection;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ForumSectionDAO
{
    private DBOperations dbOperations;
    /**
     * Обеспечивает соединение с БД при помощи Hibernate (в Session)
     */
    public ForumSectionDAO()
    {
        dbOperations = new DBOperations();
    }


    public void save(ForumSection forumSection)
    {
        dbOperations.createEntity(forumSection);
    }

    public void update(ForumSection forumSection)
    {
        if(forumSection.getOldSectionName() == null)
            return;

        if(!(forumSection.getOldSectionName().equals(forumSection.getSectionName())))
            dbOperations.nativeQueryUpdates("update РАЗДЕЛЫ_ФОРУМА set РАЗДЕЛ = \'"+forumSection.getSectionName()+
                    "\' where РАЗДЕЛ =  \'"+forumSection.getOldSectionName()+"\'");
    }

    public void delete(ForumSection forumSection)
    {
        dbOperations.deleteEntity(forumSection);
    }

    public ForumSection getByID(String id) throws EntityNotFoundException
    {
        return (ForumSection)dbOperations.findEntityByObjectId(ForumSection.class, id);
    }

    public List<ForumSection> getAllSections()
    {
        return DBOperations.selectAll(ForumSection.class);
    }

}
