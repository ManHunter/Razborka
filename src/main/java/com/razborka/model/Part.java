package com.razborka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Entity
@Table(name = "part")
public class Part implements Serializable {

    private int id;
    private PartGroup group;
    private PartType type;
    private Car car;
    private String condition;
    private BigDecimal price;
    private int catalogNumber;
    private String description;
    private LocalDateTime date;

    private List<Photo> photos;

    public Part() {
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
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "car_id", nullable = true, unique = false)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "part_group_id", nullable = true, unique = false)
    public PartGroup getGroup() {
        return group;
    }

    public void setGroup(PartGroup group) {
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name = "part_type_id", nullable = true, unique = false)
    public PartType getType() {
        return type;
    }

    public void setType(PartType type) {
        this.type = type;
    }

    @Column(name = "part_condition")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "catalog_num")
    public int getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(int catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
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

    @JsonIgnore
    @Override
    public String toString() {
        return  "partGroup: " + group +
                "partType: " + type +
                "car: " + car +
                "condition: " + condition +
                "price: " + price +
                "catalogNumber: " + catalogNumber +
                "date: " + date +
                "description: " + description;

    }
}
