package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
@Table(name="ДНЕВНИК")
public class Dictionary
{
    @Id
    @Column(name="ИД_ДНЕВНИК", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @Column(name="ТЕМА")
    private String topic;

    @Column(name="ЗАПИСЬ")
    private String text;

    @Column(name="ДАТА")
    private Date date;

    public Dictionary(){}

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}