package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.GenerationRepository;
import com.gcruz.pokeapi.service.GenerationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GenerationServiceImpl implements GenerationService {

    private final GenerationRepository repository;

    public GenerationServiceImpl(GenerationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Generation create(Generation generation) throws Exception {
        try {
            validateGeneration(generation);
            return repository.save(generation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Generation> findAll() throws Exception {
        try {
            return (List<Generation>) repository.findAll();

        } catch (Exception e) {
            throw new Exception("Error while fetching all generation.");
        }
    }

    @Override
    public Generation findById(long id) throws NotFoundException {
        Optional<Generation> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else throw new NotFoundException(String.format("Generation with id %s not found", id));
    }

    @Override
    public void update(long id, Generation generation) throws Exception {
        try {
            generation.setId(id);
            repository.save(generation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void validateGeneration(Generation generation) throws Exception {
        if (Objects.isNull(generation.getName())) {
            throw new Exception("Unable to create Pokemon, Name value must not be null");
        }
    }
}
