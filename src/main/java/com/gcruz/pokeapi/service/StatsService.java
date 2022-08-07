package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.repository.model.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface StatsService {
    Stats create(Stats stats) throws Exception;

    List<Stats> findAll() throws Exception;

    Stats findById(long id) throws NotFoundException;

    Stats update(Stats stats) throws Exception;

    void deleteById(long id) throws Exception;
}
