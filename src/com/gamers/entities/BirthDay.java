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
    private long id1;

    @ManyToOne
    @JoinColumn(name="ИД_Именинника")
    private long id2;


    public BirthDay()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param id1 ИД События
     * @param id2 ИД Именинника
     */
    public BirthDay(long id1, long id2)
    {
        this.id1 = id1;
        this.id2 = id2;
    }

    public long getId()
    {
        return id;
    }

    public long getId1() {
        return id1;
    }

    public void setId1(long id1) {
        this.id1 = id1;
    }

    public long getId2() {
        return id2;
    }

    public void setId2(long id2) {
        this.id2 = id2;
    }
}
