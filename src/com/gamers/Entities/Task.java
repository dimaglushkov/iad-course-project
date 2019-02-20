package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ЗАДАЧА")
public class Task implements Serializable
{
    @Id
    @Column(name="ИД_ЗАДАЧА", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @Column(name="ДАТА")
    private Date date;

    @Column(name="СТАТУС")
    private Boolean status;

    public Task(){}

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
