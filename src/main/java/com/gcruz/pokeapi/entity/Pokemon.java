package com.gcruz.pokeapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pokemons")
public class Pokemon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_id")
    private Long id;
    @NotNull
    private String name;
    private int height;
    private int weight;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;
    @JsonIgnoreProperties("pokemon")
    @OneToOne
    @JoinColumn(name = "stats_id", unique = true)
    private Stats stats;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    @JsonIgnoreProperties("pokemonList")
    @ManyToMany
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> types;
    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "artwork_id")
    private Artwork artwork;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, int height, int weight, Generation generation, Stats stats, Region region,
                   List<Type> types, Artwork artwork) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.generation = generation;
        this.stats = stats;
        this.region = region;
        this.types = types;
        this.artwork = artwork;
    }

    public Long getId() {
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artWork) {
        this.artwork = artWork;
    }
}
