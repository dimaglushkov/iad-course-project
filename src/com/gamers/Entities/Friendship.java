package com.gamers.Entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ДРУЖБА")
public class Friendship implements Serializable
{
    @Id
    @Column(name="ИД_ДРУЖБА", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @OneToOne
    @JoinColumn(name = "ИД_ДРУГ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person friend;

    @Column(name = "ПОДТВЕРЖДЕНО")
    private boolean isConfirmed;

    public Friendship()
    {}

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Person getFriend()
    {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    public boolean isConfirmed()
    {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed)
    {
        isConfirmed = confirmed;
    }
}
