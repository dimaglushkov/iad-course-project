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
 * Это класс обеспечивающий описание сущности <strong>Г_Заметка</strong> в виде класса
 */
@Entity
@Table(name="Г_Заметка")
public class Note
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="ИД_Пользователя")
    private long id1;

    @Column(name="Дата")
    private Date date;

    @Column(name="Текст")
    private String text;


    public Note()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param id1
     * @param date
     * @param text
     */
    public Note(long id1, Date date, String text)
    {
        this.id1 = id1;
        this.date = date;
        this.text = text;

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
}