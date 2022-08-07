package com.gcruz.pokeapi.dto;

import lombok.Data;

@Data
public class ArtworkDTO {
    private Long id;
    private String url;
    private Long pokemonId;
}
