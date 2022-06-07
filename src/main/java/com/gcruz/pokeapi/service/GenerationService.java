package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface GenerationService {
    Generation create(Generation generation) throws Exception;

    List<Generation> findAll() throws Exception;

    Generation findById(long id) throws NotFoundException;

    void update(long id, Generation generation) throws Exception;

    void deleteById(long id) throws Exception;
}