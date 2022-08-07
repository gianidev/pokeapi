package com.gcruz.pokeapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonDTO {
    private Long id;
    private String name;
    private int height;
    private int weight;
    private String generation;
    private String region;
    private List<String> types;
    private String artwork;
}
