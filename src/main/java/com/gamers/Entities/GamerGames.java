package com.gamers.Entities;

import javax.persistence.*;
import javax.persistence.GenerationType;

/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_ЛИЧН_ИГРА</strong> в виде класса
 */
@Entity
@Table(name="ЛИЧН_ИГРА")
public class GamerGames
{
    @Id
    @Column(name="ИД_ЛИЧН_ИГРА", columnDefinition = "INT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ИД_ИГРА", referencedColumnName = "ИД_ИГРА")
    private Game game;


    public GamerGames(){}



}