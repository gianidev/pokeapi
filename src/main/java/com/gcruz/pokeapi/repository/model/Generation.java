package com.gcruz.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "generations")
@Data
@NoArgsConstructor
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generation_id")
    private Long id;
    @NotNull
    private String name;
    @OneToOne
    @JoinColumn(name = "region_id")
    private Region mainRegion;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "generation")
    private List<Pokemon> pokemonList;
}
