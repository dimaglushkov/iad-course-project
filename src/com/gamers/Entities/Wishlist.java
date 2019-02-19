package com.gamers.Entities;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ЖЕЛАЕМОЕ")
public class Wishlist
{
    @Id
    @Column(name = "ИД_ЖЕЛАМОЕ", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(columnDefinition = "ИД_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person person;

    @OneToOne
    @JoinColumn(columnDefinition = "ИД_ИГРА", referencedColumnName = "ИД_ИГРА")
    private Game game;



}
