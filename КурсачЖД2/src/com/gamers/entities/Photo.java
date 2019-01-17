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
 * Это класс обеспечивающий описание сущности <strong>Г_Фотография</strong> в виде класса
 */
@Entity
@Table(name="Г_Фотография")
public class Photo
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="Название")
    private String name;

    @Column(name="Когда_добавлено")
    private Date date;

    @Column(name="URL")
    private String url;

    public Photo()
    {

    }

    public Photo(String name, Date date, String url)
    {
        this.name = name;
        this.date = date;
        this.url = url;
    }

    public long getId()
    {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}