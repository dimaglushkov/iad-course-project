package com.boatguys.entities;

import javax.persistence.*;
import java.util.Date;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ЛИЧНЫЕ_СООБЩЕНИЯ</strong> в виде класса
 */
@Entity
@Table(name="ЛИЧНЫЕ_СООБЩЕНИЯ")
public class Message
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @OneToOne
	@JoinColumn(name = "ИД_ОТПРАВИТЕЛЬ")
    private User sender;

    @OneToOne
	@JoinColumn(name = "ИД_ПОЛУЧАТЕЛЯ")
    private User receiver;

    @Column(name="СООБЩЕНИЕ")
    private String messageText;

    @Column(name="ДАТА")
    private Date date;

    public Message()
    {
    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param sender Пользователь, отправивший сообшение
     * @param receiver Пользователь, получивший сообщение
     * @param messageText ДеФакто текст сообщения
     * @param date Дата и время отправки сообщения
     */
    public Message(User sender, User receiver, String messageText, Date date)
    {
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = messageText;
        this.date = date;
    }

    public long getId()
    {
        return id;
    }

    public User getSender()
    {
        return sender;
    }

    public User getReceiver()
    {
        return receiver;
    }

    public String getMessageText()
    {
        return messageText;
    }

    public Date getDate()
    {
        return date;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public void setReceiver(User receiver)
    {
        this.receiver = receiver;
    }

    /**
     * Метод устанавливает текст сообшения
     * @param messageText Строка - сообшение, которое будет установлено
     */
    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (!sender.equals(message.sender)) return false;
        if (!receiver.equals(message.receiver)) return false;
        if (!messageText.equals(message.messageText)) return false;
        return date.equals(message.date);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + sender.hashCode();
        result = 31 * result + receiver.hashCode();
        result = 31 * result + messageText.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}