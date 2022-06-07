package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stats")
public class StatsController {

    @Autowired
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

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Stats stats) throws Exception {
        service.update(id, stats);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
