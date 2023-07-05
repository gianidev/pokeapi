package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Generation;

import java.util.List;

public interface GenerationService {
    Generation createGeneration(Generation generation) throws Exception;

    List<Generation> getAllGenerations() throws Exception;

    Generation getGenerationById(long id) throws NotFoundException;

    Generation updateGeneration(Generation generation) throws Exception;

    void deleteGeneration(long id) throws NotFoundException;
}