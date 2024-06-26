package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.model.Pokemon;

import java.util.List;

public interface PokemonService {
    Pokemon createPokemon(Pokemon pokemon) throws Exception;

    List<Pokemon> getAllPokemon() throws Exception;

    Pokemon getPokemonById(long id) throws NotFoundException;

    Pokemon getPokemonByName(String name) throws NotFoundException;

    Pokemon updatePokemon(Pokemon pokemon) throws Exception;

    void deletePokemon(long id) throws NotFoundException;
}
