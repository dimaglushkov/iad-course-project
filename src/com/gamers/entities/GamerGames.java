package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_ЛИЧН_ИГРА</strong> в виде класса
 */
@Entity
@Table(name="П_ЛИЧН_ИГРА")
public class GamerGames
{
    @Id
    @Column(name="ИД_ЛИЧН_ИГРА", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "ИД_ИГРА")
    private Game game;


    public GamerGames()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param game ИД Игры
     * @see Game
     * @param owner ИД Владельца игры
     * @see User
     */
    public GamerGames(Game game, User owner)
    {
        this.game = game;
        this.owner = owner;
    }

    public long getId()
    {
        return id;
    }

    public Game getGame()
    {
        return game;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj == null)
            return false;

        if(!(obj instanceof GamerGames))
            return false;
        GamerGames gamergames = (GamerGames)obj;

        return id == gamergames.getId() && game.equals(gamergames.getGame());

    }
}