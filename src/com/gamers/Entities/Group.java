package com.gamers.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ГРУППА_ЛИЧН")
public class Group implements Serializable
{

    @Id
    @Column(name ="ИД_ГРУППА_ЛИЧН", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;

    @Column(name = "ГРУППА")
    private String groupName;


    @Column(name = "НИКНЕЙМ")
    private String nickname;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Group(){
    }

    public Group(String groupName)
    {
        this.groupName = groupName;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
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
