package com.boatguys.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>БАЗОВЫЕ_ЛОДКИ</strong> в виде класса
 */
@Entity
@Table(name = "БАЗОВЫЕ_ЛОДКИ")
public class BaseBoat
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;    

    @Column(name="ТИП")
    private String type;

    @Column(name="МАТЕРИАЛ")
    private String material;

    @Column(name="ГРУЗОПОДЪЕМНОСТЬ")
    private int loadCapacity;

    @Column(name="МАКС_СКОРОСТЬ")
    private int maxSpeed;

    @Column(name="УСКОРЕНИЕ")
    private int boost;

    @Column(name="УПРАВЛЯЕМОСТЬ")
    private int controllability;

    @Column(name="ВЕС")
    private double weight;

    @Column(name="ЦЕНА")
    private double price;

    public BaseBoat()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param type Тип базовой лодки
     * @param material Материал базовой лодки
     * @param loadCapacity Грузодьемность базовой лодки
     * @param maxSpeed Максимальная скорость базовой лодки
     * @param boost Ускорение, которое развивает базовая лодка
     * @param controllability Управляемость базовой лодки
     * @param weight Вес базовой лодки
     * @param price Цена за базавую лодку
     */
    public BaseBoat( String type, String material, int loadCapacity, 
                    int maxSpeed, int boost, int controllability, double weight, double price)
    {
        this.type = type;
        this.material = material;
        this.loadCapacity = loadCapacity;
        this.maxSpeed = maxSpeed;
        this.boost = boost;
        this.controllability = controllability;
        this.weight = weight;
        this.price = price;
    
    }

    public long getId()
    {
        return id;
    }
    
    public String getType()
    {
        return type;
    }

    public String getMaterial()
    {
        return material;
    }


    public int getLoadCapacity()
    {
        return loadCapacity;
    }

    public int getMaxSpeed()
    {
        return maxSpeed;
    }

    public int getBoost()
    {
        return boost;
    }

    public int getControllability()
    {
        return controllability;
    }

    public double getWeight()
    {
        return weight;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj == null)
            return false;


        if(!(obj instanceof BaseBoat))
            return false;
               
        BaseBoat baseBoat = (BaseBoat)obj;

        return id == baseBoat.getId() && type.equals(baseBoat.getType())
                && material.equals(baseBoat.getMaterial()) && loadCapacity == baseBoat.getLoadCapacity()
                && maxSpeed == baseBoat.getMaxSpeed() && boost == baseBoat.getBoost()
                && controllability == baseBoat.getControllability() && weight == baseBoat.getWeight()
                && price == baseBoat.getPrice(); 
    }
}