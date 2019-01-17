package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.*;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_День_Рождения</strong> в виде класса
 */
@Entity
@Table(name="Г_День_рождения")
@EqualsAndHashCode
public class BirthDay
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_События")
    private Event event;

    @ManyToOne
    @JoinColumn(name="ИД_Именинника")
    private User user;

    public BirthDay()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param event ИД События
     * @param user ИД Именинника
     */
    public BirthDay(Event event, User user)
    {
        this.event = event;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
