package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.repository.model.Region;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("region")
@AllArgsConstructor
public class RegionController {

    RegionService service;

    @GetMapping
    private ResponseEntity<List<Region>> findAll() throws Exception {
        return new ResponseEntity<List<Region>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Region> findById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<Region>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> create(@RequestBody Region region) throws Exception {
        return new ResponseEntity<Region>(service.create(region), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Region> update(@RequestBody Region region) throws Exception {
        return new ResponseEntity<Region>(service.update(region), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
