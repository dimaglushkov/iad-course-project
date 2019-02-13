package com.boatguys.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ТЮНИНГ_АТЕЛЬЕ</strong> в виде класса
 */
@Entity
@Table(name="ТЮНИНГ_АТЕЛЬЕ")
public class TuningStudio
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="НАЗВАНИЕ")
    private String name;

    @Column(name="АДРЕС")
    private String address;

    @Column(name="ТЕЛЕФОН")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @OneToMany( fetch = FetchType.EAGER)
    @JoinColumn(name = "ИД")
    private Set<TuningService> services = new LinkedHashSet<>();
    
    public TuningStudio()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name Название ателье
     * @param address Место еахлждения (адрес)
     * @param phoneNumber Номер телефона организации
     * @param email Электронная почта ателье
     */
    public TuningStudio(String name, String address, String phoneNumber, String email)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public Set<TuningService> getServices()
    {
        return services;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Метод устанавлиает ИД услуг для ателье
     * @param service ИД услуги (зависимость многие ко многим с тюнинг_услугами)
     * @see TuningService
     */
    public void addService(TuningService service)
    {
        this.services.add(service);
    }

    public void addService(List<TuningService> services)
    {
        this.services.addAll(services);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TuningStudio that = (TuningStudio) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!address.equals(that.address)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        if (!email.equals(that.email)) return false;
        return services.equals(that.services);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + services.hashCode();
        return result;
    }
}