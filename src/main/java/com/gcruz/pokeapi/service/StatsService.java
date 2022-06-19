package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface StatsService {
    Stats create(Stats stats) throws Exception;

    List<Stats> findAll() throws Exception;

    Stats findById(long id) throws NotFoundException;

    void update(Stats stats) throws Exception;

    Stats partialUpdate(long id, JsonPatch patch) throws Exception;

    void deleteById(long id) throws Exception;
}
