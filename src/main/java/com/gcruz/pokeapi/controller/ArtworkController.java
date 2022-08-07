package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.ArtworkDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Artwork;
import com.gcruz.pokeapi.service.ArtworkService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("artwork")
@AllArgsConstructor
public class ArtworkController {

    @Autowired
    private ModelMapper modelMapper;

    private ArtworkService service;


    @GetMapping
    private ResponseEntity<List<ArtworkDTO>> getAllArtworks() throws Exception {

        List<ArtworkDTO> artworkResponse = service.getAllArtworks()
                .stream()
                .map(artwork -> modelMapper.map(artwork, ArtworkDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(artworkResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtworkDTO> getArtworkById(@PathVariable("id") Long id) throws NotFoundException {

        Artwork artwork = service.getArtworkById(id);
        ArtworkDTO artworkResponse = modelMapper.map(artwork, ArtworkDTO.class);
        return ResponseEntity.ok().body(artworkResponse);
    }

    @PostMapping
    public ResponseEntity<ArtworkDTO> createArtwork(@RequestBody ArtworkDTO artworkDTO) throws Exception {

        Artwork artworkRequest = modelMapper.map(artworkDTO, Artwork.class);
        Artwork artwork = service.createArtwork(artworkRequest);
        ArtworkDTO artworkResponse = modelMapper.map(artwork, ArtworkDTO.class);

        return new ResponseEntity<ArtworkDTO>(artworkResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ArtworkDTO> updateArtwork(@RequestBody ArtworkDTO artworkDTO) throws Exception {

        Artwork artworkRequest = modelMapper.map(artworkDTO, Artwork.class);
        Artwork artwork = service.updateArtwork(artworkRequest);
        ArtworkDTO artworkResponse = modelMapper.map(artwork, ArtworkDTO.class);
        return ResponseEntity.ok().body(artworkResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteArtwork(@PathVariable("id") Long id) throws Exception {

        service.deleteArtwork(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

