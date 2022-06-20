package com.gcruz.pokeapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne
    private DamageRelation damageRelation;
    @ManyToMany
    private List<Pokemon> pokemonList;

    public Type() {
    }

    public Type(long id, String name, DamageRelation damageRelation,
                List<Pokemon> pokemonList) {
        this.id = id;
        this.name = name;
        this.damageRelation = damageRelation;
        this.pokemonList = pokemonList;
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

    public DamageRelation getDamageRelation() {
        return damageRelation;
    }

    public void setDamageRelation(DamageRelation damageRelation) {
        this.damageRelation = damageRelation;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

}
