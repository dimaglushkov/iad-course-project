package com.gamers.entities;

import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.*;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_Встреча</strong> в виде класса
 */
@Entity
@Table(name="Г_Встреча")
@EqualsAndHashCode
public class Meeting
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_События")
    private Event event;

    @ManyToOne
    @JoinColumn(name="ИД_Места")
    private Place place;

    @ManyToOne
    @JoinColumn(name="ИД_Вида")
    private MeetingType type;

    @Column(name="Наличие_дресс_кода")
    private boolean dress;


    public Meeting()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param event ИД События
     * @param place ИД Места встречи
     * @param type ИД Вида встречи
     * @param dress Наличие дресс кода
     */
    public Meeting(Event event, Place place, MeetingType type, boolean dress)
        {
         this.event = event;
         this.place = place;
         this.type = type;
         this.dress = dress;
        }

    public long getId()
    {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public boolean isDress() {
        return dress;
    }

    public void setDress(boolean dress) {
        this.dress = dress;
    }
}