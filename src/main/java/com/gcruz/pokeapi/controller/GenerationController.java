package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.GenerationDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.model.Generation;
import com.gcruz.pokeapi.service.GenerationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("generation")
@AllArgsConstructor
public class GenerationController {

    @Autowired
    private ModelMapper modelMapper;

    GenerationService service;

    @GetMapping
    private ResponseEntity<List<GenerationDTO>> getAllGenerations() throws Exception {

        List<GenerationDTO> generationResponse = service.getAllGenerations()
                .stream()
                .map(generation -> modelMapper.map(generation, GenerationDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(generationResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenerationDTO> getGenerationById(@PathVariable("id") long id) throws NotFoundException {

        Generation generation = service.getGenerationById(id);
        GenerationDTO generationResponse = modelMapper.map(generation, GenerationDTO.class);
        return ResponseEntity.ok().body(generationResponse);
    }

    @PostMapping
    public ResponseEntity<GenerationDTO> createGeneration(@RequestBody GenerationDTO generationDTO) throws Exception {
        Generation generationRequest = modelMapper.map(generationDTO, Generation.class);
        Generation generation = service.createGeneration(generationRequest);
        GenerationDTO generationResponse = modelMapper.map(generation, GenerationDTO.class);

        return new ResponseEntity<GenerationDTO>(generationResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GenerationDTO> update(@RequestBody GenerationDTO generationDTO) throws Exception {

        Generation generationRequest = modelMapper.map(generationDTO, Generation.class);
        Generation generation = service.updateGeneration(generationRequest);
        GenerationDTO generationResponse = modelMapper.map(generation, GenerationDTO.class);
        return ResponseEntity.ok().body(generationResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) throws Exception {

        service.deleteGeneration(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
