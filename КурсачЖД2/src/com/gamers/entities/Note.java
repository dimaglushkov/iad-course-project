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

    @ManyToOne
    @JoinColumn(name="ИД_Пользователя")
    private User user;

    @Column(name="Дата")
    private Date date;

    @Column(name="Текст")
    private String text;


    public Note()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param user
     * @param date
     * @param text
     */
    public Note(User user, Date date, String text)
    {
        this.user = user;
        this.date = date;
        this.text = text;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}