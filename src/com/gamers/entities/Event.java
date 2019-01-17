package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.*;

import java.sql.Time;
import java.util.Date;

/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_СОБЫТИЕ</strong> в виде класса
 */
@Entity
@Table(name="П_СОБЫТИЕ")
@EqualsAndHashCode
public class Event
{
    @Id
    @Column(name="ИД_СОБЫТИЕ", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_ЛИЧНОСТЬ")
    private long id1;

    @Column(name="ОПИСАНИЕ")
    private String desc;

    @Column(name="ДАТА")
    private Date date;

    @Column(name="ВРЕМЯ_НАЧ")
    private Time time1;

    @Column(name="ВРЕМЯ_КОН")
    private Time time2;

    public Event()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param desc Описание события
     * @param date Дата события
     * @param time1 Время начала
     * @param time2 Время окончания
     */
    public Event(String desc, Date date, Time time1, Time time2)
    {
        this.desc = desc;
        this.date = date;
        this.time1 = time1;
        this.time2 = time2;
    }

    public long getId()
    {
        return id;
    }


}