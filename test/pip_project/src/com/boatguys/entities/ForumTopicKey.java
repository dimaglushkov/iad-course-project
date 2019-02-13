package com.boatguys.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ForumTopicKey implements Serializable, Cloneable
{
    @ManyToOne()
    @JoinColumn(name = "РАЗДЕЛ")
    private ForumSection section;
    @Column(name = "ТЕМА")
    private String topicName;

    public ForumTopicKey()
    {
    }

    public ForumTopicKey(ForumSection section, String topicName)
    {
        this.section = section;
        this.topicName = topicName;
    }

    public ForumSection getSection()
    {
        return section;
    }

    public void setSection(ForumSection section)
    {
        this.section = section;
    }

    public String getTopicName()
    {
        return topicName;
    }

    public void setTopicName(String topicName)
    {
        this.topicName = topicName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumTopicKey that = (ForumTopicKey) o;
        return Objects.equals(section, that.section) &&
                Objects.equals(topicName, that.topicName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(section, topicName);
    }

    @Override
    public ForumTopicKey clone()
    {
        ForumTopicKey clone = new ForumTopicKey();

        clone.setSection(this.section);
        clone.setTopicName(this.getTopicName());

        return clone;
    }
}
