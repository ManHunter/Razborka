package com.razborka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Entity
@Table(name = "fuel_type")
public class Fuel implements Serializable {

    private int id;
    private String name;

    private List<Car> cars;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fuel")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> engines) {
        this.cars = engines;
    }
}
