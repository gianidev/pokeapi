package com.gcruz.pokeapi.dto;

import lombok.Data;

@Data
public class StatsDTO {
    private Long id;
    private int healthPoints;
    private int attack;
    private int defense;
    private int speedAttack;
    private int speedDefense;
    private int speed;
    private int total;
    private Long pokemonId;
}
