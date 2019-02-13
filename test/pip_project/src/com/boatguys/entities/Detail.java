package com.boatguys.entities;

import javax.persistence.*;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ДЕТАЛИ</strong> в виде класса
 */
@Entity
@Table(name="ДЕТАЛИ")
public class Detail
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="ТИП")
    private String type;

    @Column(name="ВЕС")
    private int weight;

    @Column(name="ВЛИЯНИЕ_НА_СКОРОСТЬ")
    private int speedEffect;

    @Column(name="ВЛИЯНИЕ_НА_УПРАВЛЯЕМОСТЬ")
    private int controllabilityEffect;

    @Column(name="ВЛИЯНИЕ_НА_УСКОРЕНИЕ")
    private int boostEffect;

    @Column(name="ЦЕНА")
    private int price;

    public Detail()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param type Тип детали (двигатель ...)
     * @param weight Вес детали
     * @param speedEffect Влияние на скорость лодки
     * @param controllabilityEffect Влияние на управляемость лодки
     * @param boostEffect  Влияние на ускорение лодки
     */
    public Detail(String type, int weight, int speedEffect, int controllabilityEffect, int boostEffect, int price)
    {
        this.weight = weight;
        this.speedEffect = speedEffect;
        this.controllabilityEffect = controllabilityEffect;
        this.boostEffect = boostEffect;
        this.type = type;
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

    public int getWeight()
    {
        return weight;
    }

    public int getSpeedEffect()
    {
        return speedEffect;
    }

    public int getControllabilityEffect()
    {
        return controllabilityEffect;
    }

    public int getBoostEffect()
    {
        return boostEffect;
    }

    public int getPrice()
    {
        return price;
    }

    public void setType(String type)
    {
        this.type = type;
    }    

    public void setWeight(int wieght)
    {
        this.weight = wieght;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void setSpeedEffect(int speedEffect)
    {
        this.speedEffect = speedEffect;
    }

    public void setControllabilityEffect(int controllabilityEffect)
    {
        this.controllabilityEffect = controllabilityEffect;
    }

    public void setBoostEffect(int boostEffect)
    {
        this.boostEffect = boostEffect;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detail detail = (Detail) o;

        if (id != detail.id) return false;
        if (weight != detail.weight) return false;
        if (speedEffect != detail.speedEffect) return false;
        if (controllabilityEffect != detail.controllabilityEffect) return false;
        if (boostEffect != detail.boostEffect) return false;
        if (price != detail.price) return false;
        return type.equals(detail.type);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + weight;
        result = 31 * result + speedEffect;
        result = 31 * result + controllabilityEffect;
        result = 31 * result + boostEffect;
        result = 31 * result + price;
        return result;
    }
}