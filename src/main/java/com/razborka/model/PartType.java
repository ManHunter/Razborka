package com.razborka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Entity
@Table(name = "part_type")
public class PartType implements Serializable {

    private int id;
    private String name;
    private PartGroup group;

    private List<Part> parts;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false, unique = false)
    public PartGroup getGroup() {
        return group;
    }

    public void setGroup(PartGroup group) {
        this.group = group;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
