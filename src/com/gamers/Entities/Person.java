package com.gamers.Entities;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="ЛИЧНОСТЬ")
public class Person implements Serializable
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "НИКНЕЙМ", referencedColumnName = "НИКНЕЙМ")
    private Set<Group> groups = new LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Info info;

    public Person() {}

    public Person(String nickname, String email, String password, String groupname) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        addGroup(new Group(groupname));
    }



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

    public Set<Group> getGroups()
    {
        return groups;
    }

    public void addGroup(Group group)
    {
        this.groups.add(group);
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}