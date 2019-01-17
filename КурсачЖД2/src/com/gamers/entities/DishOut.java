package com.gamers.entities;

import java.util.Date;
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
 * Это класс обеспечивающий описание сущности <strong>Г_Подача_блюда</strong> в виде класса
 */
@Entity
@Table(name="Г_Подача_блюда")
@EqualsAndHashCode
public class DishOut
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name="ИД_Места")
    private Place place;

    @ManyToOne
    @JoinColumn(name="ИД_Блюда")
    private Dish dish;

    @Column(name="Время")
    private Date date;

    public DishOut()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param place ИД Места
     * @param dish ИД Блюда
     * @param date Время
     */
    public DishOut(Place place, Dish dish, Date date)
    {
        this.place = place;
        this.dish = dish;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}