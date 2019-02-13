package com.boatguys.entities;

import javax.persistence.*;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ТЮНИНГ_УСЛУГА</strong> в виде класса
 */
@Entity
@Table(name="ТЮНИНГ_УСЛУГА")
public class TuningService
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
	@JoinColumn(name = "ИД_ДЕТАЛИ")
    private Detail detail;

    @Column(name="ЦЕНА")
    private double price;

    @Column(name="НАЗВАНИЕ")
    private String name;

    @ManyToOne
    @JoinColumn(name = "АТЕЛЬЕ")
    private TuningStudio tuningStudio;

    public TuningService()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param detail ИД леиали, которая может быть установлена, покрашена ...
     * @param price Цена за проведение работы
     * @param name Название услуги
     */
    public TuningService(Detail detail, double price, String name, TuningStudio studio)
    {
        this.detail = detail;
        this.price = price;
        this.name = name;
        this.tuningStudio = studio;
    }

    public long getId()
    {
        return id;
    }

    public Detail getDetail()
    {
        return detail;
    }

    public double getPrice()
    {
        return price;
    }

    /**
     * Метод возврашает название услуги
     * @return Строка - название услугт
     */
    public String getName()
    {
        return name;
    }

    /**
     * Метод устанавливает цену за проведение работы
     * @param price Число - которе будет установленно как цена
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Метод устанавливает значение названия услуги
     * @param name Строка - будет значениям для названия услуги
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TuningService that = (TuningService) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (!detail.equals(that.detail)) return false;
        if (!name.equals(that.name)) return false;
        return tuningStudio.equals(that.tuningStudio);
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + detail.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + tuningStudio.hashCode();
        return result;
    }
}
