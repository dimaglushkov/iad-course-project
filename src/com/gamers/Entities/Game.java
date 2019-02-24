package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.sql.Date;

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

    @Column(name="ДАТА_ВЫХОДА", columnDefinition = "DATE")
    private Date date;

    @Column(name="ОПИСАНИЕ", columnDefinition = "TEXT")
    private String desc;

    public Game(){}

    public Game( String name, Date date, String desc) {
        this.name = name;
        this.date = date;
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

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}