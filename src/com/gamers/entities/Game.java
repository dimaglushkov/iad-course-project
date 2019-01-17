package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_Игры</strong> в виде класса
 */
@Entity
@Table(name = "Игры")
public class Game
{
    @Id
    @Column(name="ИД_ИГРА", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;    

    @Column(name="НАЗВАНИЕ")
    private String name;

    @Column(name="ГОД_ВЫХОДА")
    private Integer year;

    @Column(name="ОПИСАНИЕ ")
    private String desc;

    public Game()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name Название игры
     */
    public Game( String name) {
        this.name = name;
    }

    public long getId()
    {
        return id;
    }
    
    public String getGame()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj == null)
            return false;


        if(!(obj instanceof Game))
            return false;

        Game game = (Game)obj;

        return id == game.getId() && name.equals(game.getGame());
    }
}