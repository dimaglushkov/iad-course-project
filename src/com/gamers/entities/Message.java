package com.gamers.entities;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.GenerationType;
import lombok.EqualsAndHashCode;
/**
 * @author Черноусов Евгений, Глушков Дмитрий
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>П_СООБЩЕНИЕ</strong> в виде класса
 */
@Entity
@Table(name="П_СООБЩЕНИЕ")
@EqualsAndHashCode
public class Message
{
    @Id
    @Column(name="ИД_СООБЩЕНИЕ", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne
	@JoinColumn(name = "ОТ_ЛИЧНОСТЬ")
    private User sender;

    @OneToOne
	@JoinColumn(name = "КОМУ_ЛИЧНОСТЬ")
    private User receiver;

    @Column(name="ТЕМА")
    private String messageTopic;

    @Column(name="ТЕКСТ")
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
     * @param messageTopic Тема сообщения
     * @param messageText Текст сообщения
     * @param date Дата и время отправки сообщения
     */
    public Message(User sender, User receiver, String messageTopic, String messageText, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageTopic = messageTopic;
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