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
 * Это класс обеспечивающий описание сущности <strong>Г_Развлечение</strong> в виде класса
 */
@Entity
@Table(name="Г_Развлечение")
@EqualsAndHashCode
public class Entertainment
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="ИД_События")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "ИД_Вида_развлечения")
    private EntertainmentType type;

    public Entertainment()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param event ИД События
     * @param type ИД Вида развлечения
     */
    public Entertainment(Event event, EntertainmentType type)
        {
        this.event = event;
        this.type = type;
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

    public EntertainmentType getType() {
        return type;
    }

    public void setType(EntertainmentType type) {
        this.type = type;
    }
}