package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.GenerationRepository;
import com.gcruz.pokeapi.service.GenerationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GenerationServiceImpl implements GenerationService {

    private final GenerationRepository repository;
    private static final Logger logger = LogManager.getLogger(GenerationServiceImpl.class);

    @Autowired
    public GenerationServiceImpl(GenerationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Generation create(Generation generation) throws Exception {
        try {
            logger.info("Saving generation in database.");
            return repository.save(generation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Generation> findAll() throws Exception {
        try {
            logger.info("Fetching all generations.");
            return (List<Generation>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all generation.");
        }
    }

    @Override
    public Generation findById(long id) throws NotFoundException {
        Optional<Generation> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("Generation with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Generation with id %s was not found", id));
    }

    @Override
    public void update(long id, Generation generation) throws Exception {
        try {
            logger.info(String.format("Updating generation with id %s .", id));
            generation.setId(id);
            repository.save(generation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            logger.info(String.format("Deleting generation with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
