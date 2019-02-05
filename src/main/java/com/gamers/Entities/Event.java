package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;

import java.sql.Time;
import java.util.Date;

/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_СОБЫТИЕ</strong> в виде класса
 */
@Entity
@Table(name="СОБЫТИЕ")
public class Event
{
    @Id
    @Column(name="ИД_СОБЫТИЕ", columnDefinition = "INT")
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

    public long getId()
    {
        return id;
    }


}