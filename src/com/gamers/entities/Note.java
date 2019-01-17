package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.Set;
import java.util.LinkedHashSet;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_ДНЕВНИК</strong> в виде класса
 */
@Entity
@Table(name="П_ДНЕВНИК")
public class Note
{
    @Id
    @Column(name="ИД_ДНЕВНИК", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="ИД_ЛИЧНОСТЬ")
    private long id1;

    @Column(name="ТЕМА")
    private String topic;

    @Column(name="ЗАПИСЬ")
    private String text;

    @Column(name="ДАТА")
    private Date date;

    public Note()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param id1
     * @param date
     * @param text
     * @param topic
     */
    public Note(long id1, Date date, String text, String topic)
    {
        this.id1 = id1;
        this.date = date;
        this.text = text;
        this.topic = topic;
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

    public String getText() {
        return text;
    }

    public void setId1(long id1) {
        this.id1 = id1;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTopic() { return topic; }

    public void setTopic(String topic) { this.topic = topic; }
}