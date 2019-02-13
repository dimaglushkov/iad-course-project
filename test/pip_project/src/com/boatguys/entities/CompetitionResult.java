package com.boatguys.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "РЕЗУЛЬТАТЫ_СОРЕВНОВАНИЙ")
public class CompetitionResult
{
    @Id
    @Column(name = "ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_УЧАСТНИКА")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ИД_СОРЕВНОВАНИЯ")
    private Competition competition;

    @Column(name = "МЕСТО")
    private int rate;

    public CompetitionResult()
    {
    }

    public CompetitionResult(User user, Competition competition, int rate)
    {
        this.user = user;
        this.competition = competition;
        this.rate = rate;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Competition getCompetition()
    {
        return competition;
    }

    public void setCompetition(Competition competition)
    {
        this.competition = competition;
    }

    public int getRate()
    {
        return rate;
    }

    public void setRate(int rate)
    {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionResult that = (CompetitionResult) o;
        return id == that.id &&
                rate == that.rate &&
                user.equals(that.user) &&
                competition.equals(that.competition);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, user, competition, rate);
    }
}
