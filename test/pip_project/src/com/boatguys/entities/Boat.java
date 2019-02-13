package com.boatguys.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ЛОДКА</strong> в виде класса
 */
@Entity
@Table(name="ЛОДКА")
public class Boat
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne()
    @JoinColumn(name="ИД_БАЗ_ЛОДКИ")
    private BaseBoat baseBoat;

    @ManyToOne
	@JoinColumn(name = "ИД_ВЛАДЕЛЬЦА")
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="ИД")
    private Set<Detail> details = new LinkedHashSet<>();

    public Boat()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param baseBoat ИД базовой лодки для лодки
     * @see BaseBoat
     * @param owner ИД Владельца лодки
     * @see User
     */
    public Boat(BaseBoat baseBoat, User owner)
    {
        this.baseBoat = baseBoat;
        this.owner = owner;
    }

    public long getId()
    {
        return id;
    }

    public BaseBoat getBaseBoat()
    {
        return baseBoat;
    }

    public Set<Detail> getDetails()
    {
        return details;
    }

    public User getOwner()
    {
        return owner;
    }

    /**
     * Метод "присваивает" к лодке базовую лодку
     * @param baseBoat обьект класса BaseBoat в БД сущность <strong>БАЗОВЫЕ_ЛОДКИ</strong>)
     * @see BaseBoat
     */
    public void setBaseBoat(BaseBoat baseBoat)
    {
        this.baseBoat = baseBoat;
    }

    /**
     * Метод устанавливает детали на лодку
     * @param detail обьект класса Detail в БД сущность <strong>ДЕТАЛИ</strong>)
     * @see Detail
     */
    public void addDetail(Detail detail)
    {
        this.details.add(detail);
    }
    public void addDetail(List<Detail> details)
    {
        this.details.addAll(details);
    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj == null)
            return false;


        if(!(obj instanceof Boat))
            return false;
        
        Boat boat = (Boat)obj;

        return id == boat.getId() && baseBoat.equals(boat.getBaseBoat())
                &&  details.containsAll(boat.getDetails());

    }
}