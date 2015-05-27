package com.razborka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Entity
@Table(name = "car")
public class Car implements Serializable {

    private int id;
    private User user;
    private Brand brand;
    private Model model;
    private int volume;
    private Fuel fuel;
    private Body body;
    private Kpp kpp;
    private int year_from;
    private int year_to;
    private String photo;
    private String description;
    private LocalDateTime date;

    private List<Part> parts;
    private List<Comment> comments;

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

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false, unique = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false, unique = false)
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Column(name = "volume", nullable = false, unique = false)
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @ManyToOne
    @JoinColumn(name = "fuel_type_id", nullable = false, unique = false)
    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @ManyToOne
    @JoinColumn(name = "body_type_id", nullable = false, unique = false)
    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @ManyToOne
    @JoinColumn(name = "kpp_id", nullable = false, unique = false)
    public Kpp getKpp() {
        return kpp;
    }

    public void setKpp(Kpp kpp) {
        this.kpp = kpp;
    }

    @Column(name = "year_from")
    public int getYear_from() {
        return year_from;
    }

    public void setYear_from(int year_from) {
        this.year_from = year_from;
    }

    @Column(name = "year_to")
    public int getYear_to() {
        return year_to;
    }

    public void setYear_to(int year_to) {
        this.year_to = year_to;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Column(name = "photo", nullable = true, unique = false)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "description", nullable = true, unique = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "date", nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                "user=" + getUser().getId() +
                "brand=" + getBrand().getId() +
                "model=" + getModel().getId() +
                "volume=" + getVolume() +
                "fuel=" + getFuel().getId() +
                "body=" + getBody().getId() +
                "kpp=" + getKpp().getId() +
                "year_from=" + getYear_from() +
                "year_to=" + getYear_to();
    }
}
