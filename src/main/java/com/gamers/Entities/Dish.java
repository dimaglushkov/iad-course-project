package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name="БЛЮДО")
public class Dish
{
    @Id
    @Column(name = "ИД_БЛЮДО", columnDefinition = "INT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @Column(name = "НАЗВАНИЕ")
    private String name;

    @Column(name = "МЕСТО")
    private String place;

    @Column(name = "ЦЕНА")
    private int price;

    public Dish(){}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
