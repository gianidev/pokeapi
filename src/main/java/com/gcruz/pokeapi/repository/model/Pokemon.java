package com.gcruz.pokeapi.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@Builder
public class Pokemon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_id")
    private Long id;
    @NotNull
    private String name;
    private int height;
    private int weight;
    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;
    @OneToOne
    @JoinColumn(name = "stats_id")
    private Stats stats;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    @ManyToMany
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> types;
    @OneToOne
    @JoinColumn(name = "artwork_id")
    private Artwork artwork;
}
