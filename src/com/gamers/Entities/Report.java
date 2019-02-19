package com.gamers.Entities;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ЖАЛОБА")
public class Report {

    @Id
    @Column(name="ИД_ЖАЛОБА", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @Column(name = "ТЕМА", columnDefinition = "TEXT")
    private String topic;

    @Column(name = "ОПИСАНИЕ", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ВРЕМЯ")
    private Timestamp time;

    public Report() {}

    public Report(Person person, String topic, String description) {
        this.person = person;
        this.topic = topic;
        this.description = description;
    }

    public Timestamp getTime()
    {
        return time;
    }

    public void setTime(Timestamp time)
    {
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
