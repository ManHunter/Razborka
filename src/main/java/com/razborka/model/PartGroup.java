package com.razborka.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Entity
@Table(name = "part_group")
public class PartGroup implements Serializable {

    private int id;
    private String name;

    private List<PartType> types;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    public List<PartType> getTypes() {
        return types;
    }

    public void setTypes(List<PartType> types) {
        this.types = types;
    }
}
