package com.boatguys.entities;

import java.io.Serializable;

import javax.persistence.*;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ГАБАРИТЫ</strong> в виде класса
 */
@Entity
@Table(name="ГАБАРИТЫ")
public class BoatDimensions implements Serializable
{
    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name="ИД_БАЗ_ЛОДКИ")
    private BaseBoat baseBoat;

    @Column(name="ДЛИНА")
    private double length;

    @Column(name="ШИРИНА")
    private double width;

    @Column(name="ВЫСОТА")
    private double height;
    

    public BoatDimensions()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param baseBoat ИД базовой локи
     * @param length Длина базовой лодки
     * @param width Ширина базовой лодки
     * @param height Высота базовой лодки
     */
    public BoatDimensions(BaseBoat baseBoat, double length, double width, double height)
    {
        this.baseBoat = baseBoat;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength()
    {
        return length;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public void setLength(double length)
    {
        this.length = length;
    }
    
    public void setWidth(double width)
    {
        this.width = width;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoatDimensions that = (BoatDimensions) o;

        if (Double.compare(that.length, length) != 0) return false;
        if (Double.compare(that.width, width) != 0) return false;
        if (Double.compare(that.height, height) != 0) return false;
        return baseBoat.equals(that.baseBoat);
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = baseBoat.hashCode();
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}