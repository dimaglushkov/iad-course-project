package com.gamers.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ИНФО")
public class Info {

    @Id
    @Column(name = "ИД_ИНФО", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @Column(name = "ИМЯ", columnDefinition = "TEXT")
    private String name;

    @Column(name = "ФАМИЛИЯ", columnDefinition = "TEXT")
    private String surname;

    @Column(name = "СТРАНА", columnDefinition = "TEXT")
    private String country;

    @Column(name = "ГОРОД", columnDefinition = "TEXT")
    private String city;

    @Column(name = "ДАТА_РОЖДЕН", columnDefinition = "DATE")
    private Date birthDate;

    @Column(name = "ДАТА_РЕГИСТР", columnDefinition = "DATE")
    private Date registerDate;

    public Info(){}



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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }
}
