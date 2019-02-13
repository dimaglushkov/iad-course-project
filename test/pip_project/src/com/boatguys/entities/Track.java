package com.boatguys.entities;

import javax.persistence.*;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ТРАССЫ</strong> в виде класса
 */
@Entity
@Table(name="ТРАССЫ")
public class Track
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="НАЗВАНИЕ")
    private String name;

    @Column(name="ДЛИНА_МАРШРУТА")
    private int distance;

    @Column(name="СЛОЖНОСТЬ")
    private int complexity;

    @Column(name="МЕСТО_ПРОВЕДЕНИЯ")
    private String location;

    @Column(name="ТИП")
    private String type;

    public Track()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name Название трассы
     * @param distance Длинна трассы
     * @param complexity Сложность трасыы(задается числом)
     * @param location Место нахождения трассы
     * @param type Тип трасыы(кольцевая...)
     */
    public Track(String name, int distance, int complexity, String location, String type)
    {
        this.name = name;
        this.distance = distance;
        this.complexity = complexity;
        this.location = location;
        this.type = type;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getDistance()
    {
        return distance;
    }

    public int getComplexity()
    {
        return complexity;
    }

    public String getLocation()
    {
        return location;
    }

    public String getType()
    {
        return type;
    }

    /**
     * Метод устанавливает название трасы
     * @param name Строка - будет усановлена как название
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Метод устанавливает длину трассы
     * @param distance Целое число - устанавливается как длна
     */
    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    /**
     * МЕтод задает сложность трассы
     * @param complexity Целое число - сложность
     */
    public void setComplexity(int complexity)
    {
        this.complexity = complexity;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (id != track.id) return false;
        if (distance != track.distance) return false;
        if (complexity != track.complexity) return false;
        if (!name.equals(track.name)) return false;
        if (!location.equals(track.location)) return false;
        return type.equals(track.type);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + distance;
        result = 31 * result + complexity;
        result = 31 * result + location.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
