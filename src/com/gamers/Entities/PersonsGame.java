package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name="ЛИЧН_ИГРА")
public class PersonsGame
{
    @Id
    @Column(name="ИД_ЛИЧН_ИГРА", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ИД_ИГРА", referencedColumnName = "ИД_ИГРА")
    private Game game;

    public PersonsGame(){}

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}