package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.repository.model.Pokemon;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.PokemonRepository;
import com.gcruz.pokeapi.service.PokemonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository repository;

    @Override
    public Pokemon createPokemon(Pokemon pokemon) throws Exception {
        try {
            verifyValues(pokemon);
            log.info("Saving pokemon in database.");
            return repository.save(pokemon);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Pokemon> getAllPokemon() throws Exception {
        try {
            log.info("Fetching all Pokemon.");
            return (List<Pokemon>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all pokemon." + e.getMessage());
        }
    }

    @Override
    public Pokemon getPokemonById(long id) throws NotFoundException {
        Optional<Pokemon> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Pokemon with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Pokemon with Id %s was not found", id));
    }

    @Override
    public Pokemon getPokemonByName(String name) throws NotFoundException {
        Optional<Pokemon> optional = repository.findByName(name);
        if (optional.isPresent()) {
            log.info(String.format("Pokemon with name %s has been found.", name));
            return optional.get();
        } else throw new NotFoundException(String.format("Pokemon with name %s not found", name));
    }

    @Override
    public Pokemon updatePokemon(Pokemon pokemon) throws Exception {
        try {
            verifyValues(pokemon);
            log.info(String.format("Updating Pokemon with id %s .", pokemon.getId()));
            repository.save(pokemon);
            return getPokemonById(pokemon.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deletePokemon(long id) throws Exception {
        try {
            log.info(String.format("Deleting Pokemon with id %s.", id));

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
}
