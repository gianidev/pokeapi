package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Artwork;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("artwork")
public class ArtworkController {
    ArtworkService service;

    @Autowired
    public ArtworkController(ArtworkService service) {
        this.service = service;
    }

    @GetMapping
    private ResponseEntity<List<Artwork>> findAll() throws Exception {
        return new ResponseEntity<List<Artwork>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Artwork> findById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<Artwork>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Artwork> create(@RequestBody Artwork artWork) throws Exception {
        return new ResponseEntity<Artwork>(service.create(artWork), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Artwork> update(@RequestBody Artwork artWork) throws Exception {
        return new ResponseEntity<Artwork>(service.update(artWork), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

