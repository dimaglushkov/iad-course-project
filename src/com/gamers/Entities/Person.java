package com.gamers.Entities;

import java.util.Set;
import java.util.List;
import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="ЛИЧНОСТЬ")
public class Person
{
    @Id
    @Column(name="ИД_ЛИЧНОСТЬ", columnDefinition = "SERIAL")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="НИКНЕЙМ", columnDefinition = "TEXT UNIQUE NOT NULL")
    private String nickname;

    @Column(name="ЭЛ_ПОЧТА", columnDefinition = "TEXT UNIQUE NOT NULL")
    private String email;

    @Column(name="ХЕШ_ПАРОЛЬ", columnDefinition = "TEXT UNIQUE NOT NULL")
    private String password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public Person() {}

}