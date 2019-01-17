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

    @ManyToOne
    @JoinColumn(name="ИД_Заметки")
    private Note note;

    @ManyToOne
    @JoinColumn(name="ИД_Фотографии")
    private Photo photo;


    public PhotoAttach()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     */
    public PhotoAttach(long id, Photo photo, Note note)
    {
        this.id = id;
        this.note = note;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}

