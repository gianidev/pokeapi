package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.repository.model.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface StatsService {
    Stats createStats(Stats stats) throws Exception;

    List<Stats> getAllStats() throws Exception;

    Stats getStatsById(long id) throws NotFoundException;

    Stats updateStats(Stats stats) throws Exception;

    void deleteStats(long id) throws NotFoundException;
}
