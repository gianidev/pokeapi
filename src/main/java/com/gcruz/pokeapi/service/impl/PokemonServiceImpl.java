package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Pokemon;
import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.PokemonRepository;
import com.gcruz.pokeapi.service.GenerationService;
import com.gcruz.pokeapi.service.PokemonService;
import com.gcruz.pokeapi.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {


    private final PokemonRepository repository;
    private final GenerationService generationService;
    private final StatsService statsService;

    public PokemonServiceImpl(PokemonRepository repository, GenerationService generationService, StatsService statsService) {
        this.repository = repository;
        this.generationService = generationService;
        this.statsService = statsService;
    }

    @Override
    public Pokemon create(Pokemon pokemon) throws Exception {
        try {
            validatePokemon(pokemon);
            pokemon.setGeneration(generationService.findById(pokemon.getGeneration().getId()));
            Stats stats = statsService.create(pokemon.getStats());
            pokemon.setStats(stats);
            return repository.save(pokemon);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Pokemon> findAll() throws Exception {
        try {
            return (List<Pokemon>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all pokemon.");
        }
    }

    @Override
    public Pokemon findById(long id) throws NotFoundException {
        Optional<Pokemon> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else throw new NotFoundException(String.format("Pokemon with Id %s was not found", id));
    }

    @Override
    public Pokemon findByName(String name) throws NotFoundException {
        Optional<Pokemon> optional = repository.findByName(name);
        if (optional.isPresent()) {
            return optional.get();
        } else throw new NotFoundException(String.format("Pokemon with name %s not found", name));
    }

    @Override
    public void update(long id, Pokemon pokemon) throws Exception {
        try {
            pokemon.setId(id);
            validatePokemon(pokemon);
            repository.save(pokemon);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void validatePokemon(Pokemon pokemon) throws Exception {
        if (Objects.isNull(pokemon)) {
            throw new Exception("Required request body is missing");
        }
        if (Objects.isNull(pokemon.getGeneration()) || Objects.isNull(pokemon.getGeneration().getId())) {
            throw new Exception("Unable to create Pokemon, Generation value must not be null");
        }
    }
}
