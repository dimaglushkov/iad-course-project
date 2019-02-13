package com.boatguys.entities;

import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ВЛАДЕЛЕЦ</strong> в виде класса
 */
@Entity
@Table(name="ВЛАДЕЛЕЦ")
public class User
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="ФИО")
    private String name;

    @Column(name="ПОЛ")
    private char gender;

    @Column(name="ГОД_РОЖДЕНИЯ")
    private Date birthday;

    @Column(name="БЮДЖЕТ")
    private double balance;

    @Column(name="СПОРТИВНЫЙ_РАЗРЯД")
    private int sportRate;

    @Column(name="КОЛИЧЕСТВО_ОЧКОВ")
    private int rate;

    @Column(name="mail")
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Boat> boats = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER )
    private Set<Group> groups = new LinkedHashSet<>();

    public User()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name ФИО рользоватедя
     * @param gender Пол пользователя
     * @param birthday Дата дня рождения
     * @param balance Бюджет пользователя
     * @param email Логин пользователя (email)
     * @param password Пароль пользователя
     */
    public User(String name, char gender, Date birthday, double balance, String email, String password)
    {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.balance = balance;
        this.email = email;
        this.password = password;

        this.sportRate = 0;
        this.rate = 0;
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name ФИО рользоватедя
     * @param gender Пол пользователя
     * @param birthday Дата дня рождения
     * @param balance Бюджет пользователя
     * @param email Логин пользователя (email)
     * @param sportRate Спортивный разряд пользователя
     * @param rate Количество очков у пользователя
     */
    public User(String name, char gender, Date birthday, double balance, String email, String password,  int sportRate, int rate)
    {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.sportRate = sportRate;
        this.rate = rate;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public char getGender()
    {
        return gender;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public double getBalance()
    {
        return balance;
    }

    
    public int getSportRate()
    {
        return sportRate;
    }

    public int getRate()
    {
        return rate;
    }

    public String getEmail()
    {
        return email;
    }

    public Set<Boat> getBoats()
    {
        return boats;
    }

    public String getPassword()
    {
        return password;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setGender(char gender)
    {
        this.gender = gender;
    }
    
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
    
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    
    public void setSportRate(int sportRate)
    {
        this.sportRate = sportRate;
    }
    
    public void setRate(int rate)
    {
        this.rate = rate;
    }

    public void setPassword(String password)
    {
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

    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Устанавливает лодки для владельца
     * @param boat обьект класса Boat, будет добавлен в лодки пользователя
     */
    public void addBoat(Boat boat)
    {
        this.boats.add(boat);
    }

    public void addBoat(List<Boat> boats)
    {
        this.boats.addAll(boats);
    }

    public void addToRate(int ratePoints)
    {
        this.rate += ratePoints;
    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj == null)
            return false;


        if(!(obj instanceof User))
            return false;
               
        User user = (User)obj;

        return id == user.getId() && name.equals(user.getName()) && gender == user.getGender()
                &&  balance == user.getBalance() 
                && sportRate == user.getSportRate() && rate == user.getRate()
                && email.equals(user.getEmail()) && password.equals(user.getPassword())
                && boats.containsAll(user.getBoats());
    }

}