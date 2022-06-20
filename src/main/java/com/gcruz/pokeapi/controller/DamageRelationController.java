package com.gcruz.pokeapi.controller;


import com.gcruz.pokeapi.entity.DamageRelation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.DamageRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("damage-relation")
public class DamageRelationController {

    DamageRelationService service;

    @Autowired
    public DamageRelationController(DamageRelationService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    private ResponseEntity<List<DamageRelation>> findAll() throws Exception {
        return new ResponseEntity<List<DamageRelation>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DamageRelation> findById(@PathVariable("id") long id) throws NotFoundException {
        return new ResponseEntity<DamageRelation>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DamageRelation> create(@RequestBody DamageRelation damageRelation) throws Exception {
        return new ResponseEntity<DamageRelation>(service.create(damageRelation), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DamageRelation> update(@RequestBody DamageRelation damageRelation) throws Exception {
        return new ResponseEntity<DamageRelation>(service.update(damageRelation), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
