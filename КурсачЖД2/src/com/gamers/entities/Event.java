package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.*;

import java.util.Date;

/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_Событие</strong> в виде класса
 */
@Entity
@Table(name="Г_Событие")
@EqualsAndHashCode
public class Event
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_Пользователя")
    private User user;

    @Column(name="Название")
    private String name;

    @Column(name="Описание")
    private String desc;

    @Column(name="Тип")
    private String type;

    @Column(name="Время_начала")
    private Date date1;

    @Column(name="Время_окончания")
    private Date date2;


    public Event()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param user ИД Пользователя
     * @param name Название события
     * @param desc Описание события
     * @param type Тип события
     * @param date1 Время начала
     * @param date2 Время окончания
     */
    public Event(User user, String name, String desc, String type, Date date1, Date date2)
    {
        this.user = user;
        this.name = name;
        this.desc = desc;
        this.type = type ;
        this.date1 = date1;
        this.date2 = date2;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }
}