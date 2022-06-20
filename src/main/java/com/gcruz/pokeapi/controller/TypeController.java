package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Type;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {

    TypeService service;

    @Autowired
    public TypeController(TypeService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    private ResponseEntity<List<Type>> findAll() throws Exception {
        return new ResponseEntity<List<Type>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Type> findById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<Type>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type> create(@RequestBody Type type) throws Exception {
        return new ResponseEntity<Type>(service.create(type), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Type> update(@RequestBody Type type) throws Exception {
        return new ResponseEntity<Type>(service.update(type), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
