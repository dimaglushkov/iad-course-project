package com.gamers.entities;

import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_ЛИЧНОСТЬ</strong> в виде класса
 */
@Entity
@Table(name="П_ЛИЧНОСТЬ")
public class User
{
    @Id
    @Column(name="ИД_ЛИЧНОСТЬ", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="НИКНЕЙМ")
    private String name;

    @Column(name="ЭЛ_ПОЧТА")
    private String email;

    @Column(name="ХЕШ_ПАРОЛЬ")
    private String password;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Game> games = new LinkedHashSet<>();

    public User()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name ник рользоватедя
     * @param password Дата дня рождения
     * @param email Логин пользователя (email)
     */
    public User(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }


    public String getEmail()
    {
        return email;
    }

    public Set<Game> getGames() { return games; }

    public String getPassword()
    {
        return password;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Устанавливает игры для владельца
     * @param game обьект класса Game, будет добавлен в игры пользователя
     */
    public void addGame(Game game)
    {
        this.games.add(game);
    }

    public void addGame(List<Game> games)
    {
        this.games.addAll(games);
    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj == null)
            return false;


        if(!(obj instanceof User))
            return false;
               
        User user = (User)obj;

        return id == user.getId() && name.equals(user.getName())
                && email.equals(user.getEmail()) && password.equals(user.getPassword())
                && games.containsAll(user.getGames());
    }
    
}