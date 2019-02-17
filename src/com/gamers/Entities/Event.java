package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="СОБЫТИЕ")
public class Event
{
    @Id
    @Column(name="ИД_СОБЫТИЕ", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @Column(name="ОПИСАНИЕ")
    private String desc;

    @Column(name="ДАТА")
    private Date date;

    @Column(name="ВРЕМЯ_НАЧ")
    private Time timeBegin;

    @Column(name="ВРЕМЯ_КОН")
    private Time timeEnd;

    public Event() {}

    public Event(String desc, Date date, Time timeBegin, Time timeEnd)
    {
        this.desc = desc;
        this.date = date;
        this.timeEnd = timeEnd;
        this.timeBegin = timeBegin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Time timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }
}