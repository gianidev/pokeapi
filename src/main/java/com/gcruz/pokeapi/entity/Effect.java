package com.gcruz.pokeapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "effect")
public class Effect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String effect;
    private String shortEffect;

    public Effect() {
    }

    public Effect(long id, String effect, String shortEffect) {
        this.id = id;
        this.effect = effect;
        this.shortEffect = shortEffect;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getShortEffect() {
        return shortEffect;
    }

    public void setShortEffect(String shortEffect) {
        this.shortEffect = shortEffect;
    }
}