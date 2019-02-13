package com.boatguys.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "РАЗДЕЛЫ_ФОРУМА")
public class ForumSection
{
    @Id
    @Column(name = "РАЗДЕЛ")
    private String sectionName;

    @Transient
    private String oldSectionName = null;

    public ForumSection()
    {
    }

    public ForumSection(String sectionName)
    {
        this.sectionName = sectionName;
    }

    public String getSectionName()
    {
        return sectionName;
    }

    public void setSectionName(String sectionName)
    {
        oldSectionName = this.sectionName;
        this.sectionName = sectionName;
    }

    public String getOldSectionName()
    {
        return oldSectionName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumSection that = (ForumSection) o;
        return Objects.equals(sectionName, that.sectionName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(sectionName);
    }

    @Override
    public String toString()
    {
        return sectionName;
    }
}
