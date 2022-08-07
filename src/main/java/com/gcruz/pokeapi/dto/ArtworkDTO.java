package com.gcruz.pokeapi.dto;

import lombok.Data;

@Data
public class ArtworkDTO {
    private long id;
    private String url;
    private long pokemonId;
}
