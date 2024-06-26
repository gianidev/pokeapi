package com.gcruz.pokeapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "generations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generation_id")
    private Long id;
    @NotNull
    private String name;
    @OneToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
