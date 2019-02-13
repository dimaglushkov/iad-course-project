package com.boatguys.entities;

import javax.persistence.*;
/**
 * @author Хрулев Виктор, Возжае Артем
 * @version 1.0
 * @since beta
 * Это класс обеспечивающий описание сущности <strong>КОМЕНТАРИИ</strong> в виде класса
 */
@Entity
@Table(name="КОМЕНТАРИИ")
public class Comment
{ 
    @Id
    @Column(name="ИД", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private long id;

    @ManyToOne
    @JoinColumn(name = "ЗАПИСЬ_НА_ФОРУМЕ_ИД")
    private ForumRecord forumRecord;

    @Column(name="КОМЕНТАРИЙ")
    private String message;

    @ManyToOne
    @JoinColumn(name="АВТОР")
    private User author;

    public Comment()
    {

    }

    /**
     * Устанавливает значения по атрибутам сущности
     * @param forumRecord ИД записи на форуме
     * @param message Коментарий к записи
     * @param author Автор коментария ИД
     */
    public Comment(ForumRecord forumRecord, String message, User author)
    {
        this.forumRecord = forumRecord;
        this.message = message;
        this.author = author;
    }

    public long getId()
    {
        return id;
    }

    public ForumRecord getForumRecord()
    {
        return forumRecord;
    }

    public String getMessage()
    {
        return message;
    }

    public User getAuthor()
    {
        return author;
    }

    /**
     * Метод присваивает коментарию ИД записи на форуме
     * @param forumRecord обьект класса ForumRecord в БД сущность <strong>ЗАПИСЬ_НА_ФОРУМЕ</strong>)
     * @see ForumRecord
     */
    public void setForumRecord(ForumRecord forumRecord)
    {
        this.forumRecord = forumRecord;
    }

    /**
     * Метод "установки"  коментарии к записи
     * @param message текст коментария
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * Метод "установки" автора коментария
     * @param author обьект класса User в БД сущность <strong>ВЛАДЕЛЕЦ</strong>)
     * @see User
     */
    public void setAuthor(User author)
    {
        this.author = author;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (!forumRecord.equals(comment.forumRecord)) return false;
        if (!message.equals(comment.message)) return false;
        return author.equals(comment.author);
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + forumRecord.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}