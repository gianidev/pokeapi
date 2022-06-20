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
    @OneToOne
    private Generation generation;
    @ManyToMany
    private List<Pokemon> pokemonList;
    @ManyToMany
    private List<Move> moves;

    public Type() {
    }

    public Type(long id, String name, DamageRelation damageRelation, Generation generation,
                List<Pokemon> pokemonList, List<Move> moves) {
        this.id = id;
        this.name = name;
        this.damageRelation = damageRelation;
        this.generation = generation;
        this.pokemonList = pokemonList;
        this.moves = moves;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamageRelation(DamageRelation damageRelation) {
        this.damageRelation = damageRelation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
