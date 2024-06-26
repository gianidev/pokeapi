package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.RegionDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.model.Region;
import com.gcruz.pokeapi.service.RegionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("region")
@AllArgsConstructor
public class RegionController {

    @Autowired
    private ModelMapper modelMapper;
    private RegionService service;

    @GetMapping
    private ResponseEntity<List<RegionDTO>> findAll() throws Exception {
        List<RegionDTO> regionResponse = service.getAllRegions()
                .stream()
                .map(region -> modelMapper.map(region, RegionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(regionResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegionDTO> findById(@PathVariable("id") Long id) throws NotFoundException {
        Region region = service.getRegionById(id);
        RegionDTO regionResponse = modelMapper.map(region, RegionDTO.class);
        return ResponseEntity.ok().body(regionResponse);
    }

    @PostMapping
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO regionDTO) throws Exception {
        Region regionRequest = modelMapper.map(regionDTO, Region.class);
        Region region = service.createRegion(regionRequest);
        RegionDTO regionResponse = modelMapper.map(region, RegionDTO.class);

        return new ResponseEntity<RegionDTO>(regionResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RegionDTO> update(@RequestBody RegionDTO regionDTO) throws Exception {
        Region regionRequest = modelMapper.map(regionDTO, Region.class);
        Region region = service.updateRegion(regionRequest);
        RegionDTO regionResponse = modelMapper.map(region, RegionDTO.class);
        return ResponseEntity.ok().body(regionResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {

        service.deleteRegion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
