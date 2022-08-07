package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Generation;
import com.gcruz.pokeapi.repository.model.Pokemon;
import com.gcruz.pokeapi.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
@AllArgsConstructor
public class PokemonController {

    PokemonService service;

    @GetMapping
    private ResponseEntity<List<Pokemon>> findAll() throws Exception {
        return new ResponseEntity<List<Pokemon>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pokemon> findById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<Pokemon>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Pokemon> findByName(@RequestParam("name") String name) throws NotFoundException {
        return new ResponseEntity<Pokemon>(service.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon pokemon) throws Exception {
        return new ResponseEntity<Pokemon>(service.create(pokemon), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pokemon> update(@RequestBody Pokemon pokemon) throws Exception {
        return new ResponseEntity<Pokemon>(service.update(pokemon), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

