package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.GenerationRepository;
import com.gcruz.pokeapi.service.GenerationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class GenerationServiceImpl implements GenerationService {

    private final GenerationRepository repository;
    
    @Override
    public Generation create(Generation generation) throws Exception {
        try {
            log.info("Saving generation in database.");
            return repository.save(generation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Generation> findAll() throws Exception {
        try {
            log.info("Fetching all generations.");
            return (List<Generation>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all generation.");
        }
    }

    @Override
    public Generation findById(long id) throws NotFoundException {
        Optional<Generation> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Generation with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Generation with id %s was not found", id));
    }

    @Override
    public void update(Generation generation) throws Exception {
        try {
            log.info(String.format("Updating generation with id %s .", generation.getId()));
            repository.save(generation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            log.info(String.format("Deleting generation with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
