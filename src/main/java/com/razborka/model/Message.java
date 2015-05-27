package com.razborka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 11.04.2015.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    private int id;
    private User user;
    private User user_from;
    private String subject;
    private String message;
    private LocalDateTime date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_from", nullable = false, unique = false)
    public User getUser_from() {
        return user_from;
    }

    public void setUser_from(User user_from) {
        this.user_from = user_from;
    }

    @Column(name = "subject", nullable = false, unique = false)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "message", nullable = false, unique = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
