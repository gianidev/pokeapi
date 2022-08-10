package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.StatsDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Stats;
import com.gcruz.pokeapi.repository.model.Stats;
import com.gcruz.pokeapi.service.StatsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("stats")
@AllArgsConstructor
public class StatsController {

    @Autowired
    private ModelMapper modelMapper;
    private StatsService service;

    @GetMapping
    public ResponseEntity<List<StatsDTO>> getAllStats() throws Exception {

        List<StatsDTO> statsResponse = service.getAllStats()
                .stream()
                .map(stats -> modelMapper.map(stats, StatsDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(statsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatsDTO> getStatsByStats(@PathVariable("id") long id) throws NotFoundException {

        Stats stats = service.getStatsById(id);
        StatsDTO statsResponse = modelMapper.map(stats, StatsDTO.class);
        return ResponseEntity.ok().body(statsResponse);
    }

    @PostMapping
    public ResponseEntity<StatsDTO> createStats(@RequestBody StatsDTO statsDTO) throws Exception {

        Stats statsRequest = modelMapper.map(statsDTO, Stats.class);
        Stats stats = service.createStats(statsRequest);
        StatsDTO statsResponse = modelMapper.map(stats, StatsDTO.class);

        return new ResponseEntity<StatsDTO>(statsResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StatsDTO> updateStats(@RequestBody StatsDTO statsDTO) throws Exception {

        Stats statsRequest = modelMapper.map(statsDTO, Stats.class);
        Stats stats = service.updateStats(statsRequest);
        StatsDTO statsResponse = modelMapper.map(stats, StatsDTO.class);
        return ResponseEntity.ok().body(statsResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStats(@PathVariable("id") long id) throws Exception {

        service.deleteStats(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
