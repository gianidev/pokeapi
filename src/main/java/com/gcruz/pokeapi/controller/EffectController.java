package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Effect;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.EffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("effect")
public class EffectController {

    EffectService service;

    @Autowired
    public EffectController(EffectService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    private ResponseEntity<List<Effect>> findAll() throws Exception {
        return new ResponseEntity<List<Effect>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Effect> findById(@PathVariable("id") long id) throws NotFoundException {
        return new ResponseEntity<Effect>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Effect> create(@RequestBody Effect effect) throws Exception {
        return new ResponseEntity<Effect>(service.create(effect), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Effect effect) throws Exception {
        service.update(effect);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
