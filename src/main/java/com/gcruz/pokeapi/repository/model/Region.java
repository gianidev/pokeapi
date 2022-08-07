package com.gcruz.pokeapi.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private long id;
    private String name;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    private List<Pokemon> pokemonList;
}
