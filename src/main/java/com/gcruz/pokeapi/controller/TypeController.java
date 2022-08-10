package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.TypeDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Type;
import com.gcruz.pokeapi.service.TypeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("type")
@AllArgsConstructor
public class TypeController {

    @Autowired
    private ModelMapper modelMapper;
    private TypeService service;

    @GetMapping
    private ResponseEntity<List<TypeDTO>> getAllTypes() throws Exception {

        List<TypeDTO> typeResponse = service.getAllTypes()
                .stream()
                .map(type -> modelMapper.map(type, TypeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(typeResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TypeDTO> getTypeById(@PathVariable("id") Long id) throws NotFoundException {

        Type type = service.getTypeById(id);
        TypeDTO typeResponse = modelMapper.map(type, TypeDTO.class);
        return ResponseEntity.ok().body(typeResponse);
    }

    @PostMapping
    public ResponseEntity<TypeDTO> createType(@RequestBody TypeDTO typeDTO) throws Exception {

        Type typeRequest = modelMapper.map(typeDTO, Type.class);
        Type type = service.createType(typeRequest);
        TypeDTO typeResponse = modelMapper.map(type, TypeDTO.class);

        return new ResponseEntity<TypeDTO>(typeResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TypeDTO> updateType(@RequestBody TypeDTO typeDTO) throws Exception {

        Type typeRequest = modelMapper.map(typeDTO, Type.class);
        Type type = service.updateType(typeRequest);
        TypeDTO typeResponse = modelMapper.map(type, TypeDTO.class);
        return ResponseEntity.ok().body(typeResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteType(@PathVariable("id") Long id) throws Exception {

        service.deleteType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
