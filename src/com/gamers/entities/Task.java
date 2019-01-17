package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.*;

import java.util.Date;

/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_ЗАДАЧА</strong> в виде класса
 */
@Entity
@Table(name="П_ЗАДАЧА")
@EqualsAndHashCode
public class Task
{
    @Id
    @Column(name="ИД_ЗАДАЧА", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_ЛИЧНОСТЬ")
    private long id1;

    @Column(name="ДАТА")
    private Date date;

    @Column(name="СТАТУС")
    private Boolean status;

    public Task()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param status Статус
     * @param date Дата
     */
    public Task(Date date, Boolean status)
    {
        this.status = status;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getId1() {
        return id1;
    }

    public void setId1(long id1) {
        this.id1 = id1;
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
