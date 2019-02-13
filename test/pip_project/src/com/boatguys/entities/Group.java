package com.boatguys.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ГРУППЫ_ПОЛЬЗОВАТЕЛЕЙ")
public class Group
{
    @Id
    @Column(name = "НАЗВАНИЕ")
    private String groupName = "users";

    public Group()
    {
    }

    public Group(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(groupName);
    }
}
