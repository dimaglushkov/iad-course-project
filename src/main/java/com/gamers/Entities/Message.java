package com.gamers.Entities;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.GenerationType;


@Entity
@Table(name="СООБЩЕНИЕ")
public class Message
{
    @Id
    @Column(name="ИД_СООБЩЕНИЕ", columnDefinition = "INT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne
	@JoinColumn(name = "ОТ_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person from;

    @OneToOne
	@JoinColumn(name = "КОМУ_ЛИЧНОСТЬ", referencedColumnName = "ИД_ЛИЧНОСТЬ")
    private Person to;

    @Column(name="ТЕМА")
    private String messageTopic;

    @Column(name="ТЕКСТ")
    private String messageText;

    @Column(name="ДАТА")
    private Date date;

    public Message(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Person getTo() {
        return to;
    }

    public void setTo(Person to) {
        this.to = to;
    }

    public String getMessageTopic() {
        return messageTopic;
    }

    public void setMessageTopic(String messageTopic) {
        this.messageTopic = messageTopic;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}