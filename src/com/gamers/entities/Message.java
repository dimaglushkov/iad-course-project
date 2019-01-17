package com.gamers.entities;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.EqualsAndHashCode;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>Г_Сообщения</strong> в виде класса
 */
@Entity
@Table(name="Г_Сообщения")
@EqualsAndHashCode
public class Message
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="Дата")
    private Date date;

    @Column(name="Message")
    private String messageText;

    @OneToOne
	@JoinColumn(name = "G_from")
    private User sender;

    @OneToOne
	@JoinColumn(name = "G_to")
    private User receiver;

    @Column(name="Flag")
    private boolean flag;

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

}