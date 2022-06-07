package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.entity.Pokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
    Optional<Pokemon> findByName(String name);
}
