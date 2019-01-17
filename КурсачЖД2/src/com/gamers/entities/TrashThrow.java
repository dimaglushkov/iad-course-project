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
    private Place place;

    @Column(name="Время_дня")
    private Date date;

    public TrashThrow()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param place место
     * @param date время
     */
    public TrashThrow(Place place, Date date)
    {
        this.place = place;
        this.date = date;

    }

}