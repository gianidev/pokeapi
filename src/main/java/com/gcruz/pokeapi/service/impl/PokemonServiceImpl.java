package com.gcruz.pokeapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.entity.Pokemon;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.PokemonRepository;
import com.gcruz.pokeapi.service.GenerationService;
import com.gcruz.pokeapi.service.PokemonService;
import com.gcruz.pokeapi.service.StatsService;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            verifyValues(pokemon);
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
    public void update(Pokemon pokemon) throws Exception {
        try {
            verifyValues(pokemon);
            logger.info(String.format("Updating Pokemon with id %s .", pokemon.getId()));
            repository.save(pokemon);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Pokemon partialUpdate(long id, JsonPatch patch) throws Exception {
        try {
            logger.info(String.format("Patching Pokemon with Id %s .", id));
            Pokemon pokemon = repository.findById(id).orElseThrow(Exception::new);
            Pokemon pokemonPatched = applyPatchToPokemon(patch, pokemon);

            Generation generation = pokemon.getGeneration();
            pokemonPatched.setGeneration(generation);

            return repository.save(pokemonPatched);
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

    public void verifyValues(Pokemon pokemon) throws Exception {
        if (pokemon.getStats() == null || pokemon.getGeneration() == null) {
            throw new Exception("Object contain null values");
        }
    }

    private Pokemon applyPatchToPokemon(
            JsonPatch patch, Pokemon targetPokemon) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPokemon, JsonNode.class));
        return objectMapper.treeToValue(patched, Pokemon.class);
    }
}
