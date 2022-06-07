package com.gcruz.pokeapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne
    private Generation mainGeneration;

    public Region(long id, String name, Generation mainGeneration) {
        this.id = id;
        this.name = name;
        this.mainGeneration = mainGeneration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Generation getMainGeneration() {
        return mainGeneration;
    }

    public void setMainGeneration(Generation mainGeneration) {
        this.mainGeneration = mainGeneration;
    }
}
