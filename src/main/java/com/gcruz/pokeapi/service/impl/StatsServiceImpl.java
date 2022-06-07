package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.StatsRepository;
import com.gcruz.pokeapi.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository repository;

    @Override
    public Stats create(Stats stats) throws Exception {
        try {
            return repository.save(stats);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Stats> findAll() throws Exception {
        try {
            return (List<Stats>) repository.findAll();

        } catch (Exception e) {
            throw new Exception("Error while fetching all stats.");
        }
    }

    @Override
    public Stats findById(long id) throws NotFoundException {
        Optional<Stats> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else throw new NotFoundException(String.format("Stats with id %s not found", id));
    }


    @Override
    public void update(long id, Stats stats) throws Exception {
        try {
            stats.setId(id);
            repository.save(stats);
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
}
