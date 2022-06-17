package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.Pokemon;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface PokemonService {
    Pokemon create(Pokemon pokemon) throws Exception;

    List<Pokemon> findAll() throws Exception;

    Pokemon findById(long id) throws NotFoundException;

    Pokemon findByName(String name) throws NotFoundException;

    void update(Pokemon pokemon) throws Exception;

    void deleteById(long id) throws Exception;


}
