package com.gamers.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.*;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_Место</strong> в виде класса
 */
@Entity
@Table(name="Г_Место")
@EqualsAndHashCode
public class Place
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="Адрес")
    private String address;

    @Column(name="Открыто_ли")
    private boolean IsOpen;

    public Place()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param address
     * @param IsOpen
     */
    public Place(String address, boolean IsOpen)
    {
        this.address = address;
        this.IsOpen = IsOpen;
    }

    public long getId()
    {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public boolean isOpen() {
        return IsOpen;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpen(boolean open) {
        IsOpen = open;
    }
}
