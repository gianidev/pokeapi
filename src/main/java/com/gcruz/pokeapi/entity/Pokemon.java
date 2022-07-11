package com.gcruz.pokeapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pokemons")
@Data
@NoArgsConstructor
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
}
