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
    private Time time;



    public Event() {}

    public Event(String desc, Date date, Time time)
    {
        this.desc = desc;
        this.date = date;
        this.time = time;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}