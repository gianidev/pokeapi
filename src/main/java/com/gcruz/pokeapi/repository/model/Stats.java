package com.gcruz.pokeapi.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id")
    private Long id;
    @NotNull
    private int healthPoints;
    @NotNull
    private int attack;
    @NotNull
    private int defense;
    @NotNull
    private int speedAttack;
    @NotNull
    private int speedDefense;
    @NotNull
    private int speed;
    @NotNull
    private int total;
    @OneToOne(mappedBy = "stats")
    private Pokemon pokemon;
}
