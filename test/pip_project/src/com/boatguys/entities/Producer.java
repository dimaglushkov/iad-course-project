package com.boatguys.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ПРОИЗВОДИТЕЛЬ</strong> в виде класса
 */
@Entity
@Table(name="ПРОИЗВОДИТЕЛЬ")
public class Producer
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @Column(name="НАЗВАНИЕ_ФИРМЫ")
    private String name;

    @Column(name="ВЕБ_САЙТ")
    private String webSite;

    @Column(name="ТЕЛЕФОН")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name="РЕЙТИНГ")
    private int rate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Detail> details = new LinkedHashSet<>();

    public Producer()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param name Название фирмы, производящей детали
     * @param webSite Сайт производителя
     * @param phoneNumber Номер телефона
     * @param email Электронная почта организации
     */
    public Producer(String name, String webSite, String phoneNumber, String email)
    {
        this.name = name;
        this.webSite = webSite;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rate = 0;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getWebSite()
    {
        return webSite;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public int getRate()
    {
        return rate;
    }

    /**
     * Метод устанавливает связь призводителя с деталями
     * @return Детали этого призводителя
     */
    public Set<Detail> getDetails()
    {
        return details;
    }

    /**
     * Устанавливает наименование организации
     * @param name Строка - которая будет являться наименованием
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Метод устанавливает значение веб-сайта организации
     * @param webSite Строка - адресс веб сайта
     */
    public void setWebSite(String webSite)
    {
        this.webSite = webSite;
    }

    /**
     * Метод устанавливает номер телефона (+7 (980)-323-12-11)
     * @param phoneNumber Строка - значение номреа телефона
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Метод устанавливает email организаци (xxx121@xxx@@@.xxx)
     * @param email Строка - значения email для организации
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setRate(int rate)
    {
        this.rate = rate;
    }

    /**
     * Метод устанавливает  детали в соотвии с обьектом detail
     * @param detail обьект класса Detail - детали от определнного производителя
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producer producer = (Producer) o;

        if (id != producer.id) return false;
        if (rate != producer.rate) return false;
        if (!name.equals(producer.name)) return false;
        if (!webSite.equals(producer.webSite)) return false;
        if (!phoneNumber.equals(producer.phoneNumber)) return false;
        if (!email.equals(producer.email)) return false;
        return details.equals(producer.details);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + webSite.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + rate;
        result = 31 * result + details.hashCode();
        return result;
    }
}