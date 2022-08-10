package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.PokemonDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Pokemon;
import com.gcruz.pokeapi.service.PokemonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemon")
@AllArgsConstructor
public class PokemonController {

    @Autowired
    private ModelMapper modelMapper;
    private PokemonService service;

    @GetMapping
    private ResponseEntity<List<PokemonDTO>> getAllPokemon() throws Exception {
        List<PokemonDTO> pokemonResponse = service.getAllPokemon()
                .stream()
                .map(pokemon -> modelMapper.map(pokemon, PokemonDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(pokemonResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable("id") Long id) throws NotFoundException {

        Pokemon pokemon = service.getPokemonById(id);
        PokemonDTO pokemonResponse = modelMapper.map(pokemon, PokemonDTO.class);
        return ResponseEntity.ok().body(pokemonResponse);
    }

    @GetMapping(params = "name")
    public ResponseEntity<PokemonDTO> getPokemonByName(@RequestParam("name") String name) throws NotFoundException {

        Pokemon pokemon = service.getPokemonByName(name);
        PokemonDTO pokemonResponse = modelMapper.map(pokemon, PokemonDTO.class);
        return ResponseEntity.ok().body(pokemonResponse);
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody Pokemon pokemonDTO) throws Exception {

        Pokemon pokemonRequest = modelMapper.map(pokemonDTO, Pokemon.class);
        Pokemon pokemon = service.createPokemon(pokemonRequest);
        PokemonDTO pokemonResponse = modelMapper.map(pokemon, PokemonDTO.class);
        return new ResponseEntity<PokemonDTO>(pokemonResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PokemonDTO> updatePokemon(@RequestBody Pokemon pokemonDTO) throws Exception {

        Pokemon pokemonRequest = modelMapper.map(pokemonDTO, Pokemon.class);
        Pokemon pokemon = service.updatePokemon(pokemonRequest);
        PokemonDTO pokemonResponse = modelMapper.map(pokemon, PokemonDTO.class);
        return ResponseEntity.ok().body(pokemonResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePokemon(@PathVariable("id") Long id) throws Exception {

        service.deletePokemon(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

