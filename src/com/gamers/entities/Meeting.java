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
    private long id1;

    @ManyToOne
    @JoinColumn(name="ИД_Места")
    private long id2;

    @ManyToOne
    @JoinColumn(name="ИД_Вида")
    private long id3;

    @Column(name="Наличие_дресс_кода")
    private boolean dress;


    public Meeting()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param id1 ИД События
     * @param id2 ИД Места встречи
     * @param id3 ИД Вида встречи
     * @param dress Наличие дресс кода
     */
    public Meeting(long id1,long id2, long id3, boolean dress)
        {
         this.id1 = id1;
         this.id2 = id2;
         this.id3 = id3;
         this.dress = dress;
        }

    public long getId()
    {
        return id;
    }

}