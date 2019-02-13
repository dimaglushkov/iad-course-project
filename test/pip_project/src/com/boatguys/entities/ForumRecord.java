package com.boatguys.entities;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Set;
import java.util.LinkedHashSet;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong> в виде класса
 */
@Entity
@Table(name="ЗАПИСЬ_НА_ФОРУМЕ")
public class ForumRecord
{
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ТЕМА", referencedColumnName = "ТЕМА"),
            @JoinColumn(name = "РАЗДЕЛ", referencedColumnName = "РАЗДЕЛ")
    })
    private ForumTopic topic;

    @Column(name="СООБЩЕНИЕ")
    private String message;

    @ManyToOne
    @JoinColumn(name = "АВТОР")
    private User author;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Comment> comments;

    public ForumRecord()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param topic Тема записи
     * @param message Сообшение, написанное на форуме
     * @param author ИД автора сообшения
     * @see User
     */
    public ForumRecord(ForumTopic topic, String message, User author)
    {
        this.topic = topic;
        this.message = message;
        this.author = author;
        this.comments = new LinkedHashSet<>();
    }

    public long getId()
    {
        return id;
    }

    public ForumSection getSection()
    {
        return topic.getSection();
    }

    public ForumTopic getTopic()
    {
        return topic;
    }

    public String getMessage()
    {
        return message;
    }

    public User getAuthor()
    {
        return author;
    }

    public Set<Comment> getComments()
    {
        return comments;
    }

    /**
     * Метод устанавливает значение для раздела но форуме
     * @param section строка, которая будет являтся разделом
     */
    public void setSection(ForumSection section)
    {
        this.topic.setSection(section);
    }

    /**
     * Метод устанавливает тему для записи
     * @param topic сторка, которая будет являться темой
     */
    public void setTopic(ForumTopic topic)
    {
        this.topic = topic;
    }

    /**
     * Метод устанавливает запись
     * @param message строка - запись на форуме
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * Метод устанавливает автора (связан с сущьность ВЛАДЕЛЕЦ)
     * @param author обьект класса User (ИД)
     */
    public void setAuthor(User author)
    {
        this.author = author;
    }


    public void addComment(Comment Comment)
    {
        this.comments.add(Comment);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        
        if(!(obj instanceof ForumRecord))
            return false;
        
        ForumRecord forumRecord = (ForumRecord)obj;

        return id == forumRecord.getId()
                && topic.equals(forumRecord.getTopic()) && message.equals(forumRecord.getMessage())
                && author.equals(forumRecord.getAuthor()) && comments.containsAll(forumRecord.getComments());
    }
}