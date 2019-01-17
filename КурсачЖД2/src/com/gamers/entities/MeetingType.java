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
 * Это класс обеспечивающий описание сущности <strong>Г_Вид_встречи</strong> в виде класса
 */
@Entity
@Table(name="Г_Встреча")
@EqualsAndHashCode
public class MeetingType
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="НАЗВАНИЕ")
    private String name;


    public MeetingType()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name Название типа встречи
     */
    public MeetingType(String name)
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



    /**
     * Устанавливает наименование типа
     * @param name Строка - которая будет являться наименованием
     */
    public void setName(String name)
    {
        this.name = name;
    }

}