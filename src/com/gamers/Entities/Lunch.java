package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.sql.Date;

@Entity
@Table(name="РАЦИОН")
public class Lunch
{
    @Id
    @Column(name = "ИД_РАЦИОН", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ИД_БЛЮДО", referencedColumnName = "ИД_БЛЮДО")
    private Dish dish;

    @Column(name = "ДАТА")
    private Date date;

    public Lunch(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
