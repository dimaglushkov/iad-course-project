package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.*;

import java.util.Date;

/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_Вынос_мусораь</strong> в виде класса
 */
@Entity
@Table(name="Г_Вынос_мусора")
@EqualsAndHashCode
public class TrashThrow
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_Места")
    private long id1;

    @Column(name="Время_дня")
    private Date date;

    public TrashThrow()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param id1
     * @param date
     */
    public TrashThrow(long id1, Date date)
    {
        this.id1 = id1;
        this.date = date;

    }

    public long getId()
    {
        return id;
    }


    public long getId1() {
        return id1;
    }

    public Date getDate() {
        return date;
    }

    public void setId1(long id1) {
        this.id1 = id1;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}