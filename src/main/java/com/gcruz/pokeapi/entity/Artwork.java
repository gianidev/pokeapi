package com.gcruz.pokeapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "sprites")
public class Sprite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprite_id")
    private long id;
    private String artWork;
    private String frontFemale;
    @JsonBackReference
    @OneToOne(mappedBy = "sprite")
    private Pokemon pokemon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtWork() {
        return artWork;
    }

    public void setArtWork(String artWork) {
        this.artWork = artWork;
    }

    public String getFrontFemale() {
        return frontFemale;
    }

    public void setFrontFemale(String frontFemale) {
        this.frontFemale = frontFemale;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
