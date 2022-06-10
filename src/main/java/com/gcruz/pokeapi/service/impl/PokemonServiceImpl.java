package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Pokemon;
import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.PokemonRepository;
import com.gcruz.pokeapi.service.GenerationService;
import com.gcruz.pokeapi.service.PokemonService;
import com.gcruz.pokeapi.service.StatsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {


    private final PokemonRepository repository;
    private final GenerationService generationService;
    private final StatsService statsService;
    private static final Logger logger = LogManager.getLogger(PokemonServiceImpl.class);

    @Autowired
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

            logger.info("Saving pokemon in database.");
            return repository.save(pokemon);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Pokemon> findAll() throws Exception {
        try {
            logger.info("Fetching all Pokemon.");
            return (List<Pokemon>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all pokemon.");
        }
    }

    @Override
    public Pokemon findById(long id) throws NotFoundException {
        Optional<Pokemon> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("Pokemon with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Pokemon with Id %s was not found", id));
    }

    @Override
    public Pokemon findByName(String name) throws NotFoundException {
        Optional<Pokemon> optional = repository.findByName(name);
        if (optional.isPresent()) {
            logger.info(String.format("Pokemon with name %s has been found.", name));
            return optional.get();
        } else throw new NotFoundException(String.format("Pokemon with name %s not found", name));
    }

    @Override
    public void update(long id, Pokemon pokemon) throws Exception {
        try {
            logger.info(String.format("Updating Pokemon with id %s .", id));
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
            logger.info(String.format("Deleting Pokemon with id %s.", id));

            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void validatePokemon(Pokemon pokemon) throws Exception {
        logger.info("Validating if Pokemon is not null and if it has a Generation.");
        if (Objects.isNull(pokemon)) {
            throw new Exception("Required request body is missing");
        }
        if (Objects.isNull(pokemon.getGeneration()) || Objects.isNull(pokemon.getGeneration().getId())) {
            throw new Exception("Unable to create Pokemon, Generation value must not be null");
        }
    }
}
