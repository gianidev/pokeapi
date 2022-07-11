package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.GenerationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("generation")
@AllArgsConstructor
public class GenerationController {

    GenerationService service;

    @GetMapping
    private ResponseEntity<List<Generation>> findAll() throws Exception {
        return new ResponseEntity<List<Generation>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Generation> findById(@PathVariable("id") long id) throws NotFoundException {
        return new ResponseEntity<Generation>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Generation> create(@RequestBody Generation generation) throws Exception {
        return new ResponseEntity<Generation>(service.create(generation), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Generation generation) throws Exception {
        service.update(generation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
