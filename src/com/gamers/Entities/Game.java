package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name = "ИГРА")
public class Game
{
    @Id
    @Column(name="ИД_ИГРА", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="НАЗВАНИЕ", columnDefinition = "TEXT")
    private String name;

    @Column(name="ГОД_ВЫХОДА", columnDefinition = "INT")
    private int year;

    @Column(name="ОПИСАНИЕ", columnDefinition = "TEXT")
    private String desc;

    public Game(){}

    public Game( String name, Integer year, String desc) {
        this.name = name;
        this.year = year;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}