package com.gamers.entities;
import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Set;
import java.util.LinkedHashSet;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_Прикрепление_Фотографии</strong> в виде класса
 */
@Entity
@Table(name="Г_Г_Прикрепление_фотографии")
public class PhotoAttach
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="ИД_Заметки")
    private long id1;

    @Column(name="ИД_Фотографии")
    private long id2;


    public PhotoAttach()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности

     */
    public PhotoAttach(long id, long id1, long id2)
    {
        this.id = id;
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

    public long getId2() {
        return id2;
    }

    public void setId1(long id1) {
        this.id1 = id1;
    }

    public void setId2(long id2) {
        this.id2 = id2;
    }
}

