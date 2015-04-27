package com.razborka.model;

import javax.persistence.*;

/**
 * Created by Admin on 20.04.2015.
 */
@Entity
@Table(name = "address")
public class Address {

    private int id;
    private User user;
    private String contry;
    private String city;
    private String address;

    public Address() {
    }

    public Address(User user, String contry, String city, String address) {
        this.user = user;
        this.contry = contry;
        this.city = city;
        this.address = address;
    }

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

    @Column(name = "contry", nullable = false, unique = false)
    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    @Column(name = "city", nullable = false, unique = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "address", nullable = false, unique = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
