package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Sprite;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.SpriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sprite")
public class SpriteController {
    SpriteService service;

    @Autowired
    public SpriteController(SpriteService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    private ResponseEntity<List<Sprite>> findAll() throws Exception {
        return new ResponseEntity<List<Sprite>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sprite> findById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<Sprite>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sprite> create(@RequestBody Sprite sprite) throws Exception {
        return new ResponseEntity<Sprite>(service.create(sprite), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Sprite> update(@RequestBody Sprite sprite) throws Exception {
        return new ResponseEntity<Sprite>(service.update(sprite), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

