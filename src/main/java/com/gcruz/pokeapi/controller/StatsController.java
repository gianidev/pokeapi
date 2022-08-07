package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.repository.model.Pokemon;
import com.gcruz.pokeapi.repository.model.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stats")
@AllArgsConstructor
public class StatsController {

    StatsService service;

    @GetMapping
    public ResponseEntity<List<Stats>> findAll() throws Exception {
        return new ResponseEntity<List<Stats>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stats> findById(@PathVariable("id") long id) throws NotFoundException {
        return new ResponseEntity<Stats>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Stats> create(@RequestBody Stats stats) throws Exception {
        return new ResponseEntity<Stats>(service.create(stats), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Stats> update(@RequestBody Stats stats) throws Exception {
        return new ResponseEntity<Stats>(service.update(stats), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
