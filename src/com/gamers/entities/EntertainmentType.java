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
 * Это класс обеспечивающий описание сущности <strong>Г_Вид_развлечения</strong> в виде класса
 */
@Entity
@Table(name="Г_Вид_развлечения")
@EqualsAndHashCode
public class EntertainmentType
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="Название")
    private String name;

    public EntertainmentType()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name Название вида развлечения
     */
    public EntertainmentType(String name)
        {
        this.name = name;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}