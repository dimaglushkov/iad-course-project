package com.boatguys.entities;

import javax.persistence.*;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>СОРЕВНОВАНИЯ</strong> в виде класса
 */
@Entity
@Table(name="СОРЕВНОВАНИЯ")
public class Competition
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="УРОВЕНЬ_СОРЕВНОВАНИЙ")
    private String level;

    @Column(name="ТИП")
    private String type;

    @ManyToOne
    @JoinColumn(name = "ИД_ТРАССЫ")
    private Track track;

    public Competition()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param level Уровень соревнований (сложность)
     * @param type Тим соревнования
     * @param track ИД трассы на которой проходит соревнование
     */
    public Competition(String level, String type, Track track)
    {
        this.level = level;
        this.type = type;
        this.track = track;
    }

    public long getId()
    {
        return id;
    }

    public String getLevel()
    {
        return level;
    }

    public String getType()
    {
        return type;
    }

    public Track getTrack()
    {
        return track;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setTrackId(Track track)
    {
        this.track = track;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Competition that = (Competition) o;

        if (id != that.id) return false;
        if (!level.equals(that.level)) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return track.equals(that.track);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + level.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + track.hashCode();
        return result;
    }
}