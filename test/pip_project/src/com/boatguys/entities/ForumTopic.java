package com.boatguys.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ТЕМЫ_ФОРУМА")
public class ForumTopic
{
    @EmbeddedId
    private ForumTopicKey id;

    @Transient
    private ForumTopicKey oldKey = null;

    public ForumTopic()
    {
    }

    public ForumTopic(ForumSection section, String topicName)
    {
        id = new ForumTopicKey(section,topicName);
    }

    public String getTopicName()
    {
        return id.getTopicName();
    }

    public void setTopicName(String topicName)
    {
        this.oldKey = id.clone();
        this.id.setTopicName(topicName);
    }

    public ForumSection getSection()
    {
        return id.getSection();
    }

    public void setSection(ForumSection section)
    {
        this.oldKey = id.clone();
        this.id.setSection(section);
    }

    public ForumTopicKey getId()
    {
        return id;
    }

    public ForumTopicKey getOldKey()
    {
        return oldKey;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumTopic that = (ForumTopic) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }


}
