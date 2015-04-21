package com.razborka.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 11.04.2015.
 */
@Entity
@Table(name = "service")
public class Service implements Serializable {

    private int id;
    private User user;
    private RepairType type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "repair_type_id")
    public RepairType getType() {
        return type;
    }

    public void setType(RepairType type) {
        this.type = type;
    }
}
