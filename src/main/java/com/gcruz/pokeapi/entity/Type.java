package com.gcruz.pokeapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private long id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "damage_relation_id")
    private DamageRelation damageRelation;
    @ManyToMany
    @JoinTable(
            name = "pokemons_type",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Pokemon> pokemonList;

    public Type() {
    }

    public Type(long id, String name, DamageRelation damageRelation,
                List<Pokemon> pokemonList) {
        this.id = id;
        this.name = name;
        this.damageRelation = damageRelation;
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
