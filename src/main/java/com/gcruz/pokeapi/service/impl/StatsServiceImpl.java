package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.StatsRepository;
import com.gcruz.pokeapi.service.StatsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {

    private final StatsRepository repository;

    @Override
    public Stats create(Stats stats) throws Exception {
        try {
            log.info("Saving Stats in database.");
            return repository.save(stats);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Stats> findAll() throws Exception {
        try {
            log.info("Fetching all Stats.");
            return (List<Stats>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all stats.");
        }
    }

    @Override
    public Stats findById(long id) throws NotFoundException {
        Optional<Stats> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Stats with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Stats with id %s not found", id));
    }


    @Override
    public void update(Stats stats) throws Exception {
        try {
            log.info(String.format("Updating Stats with id %s .", stats.getId()));
            repository.save(stats);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            log.info(String.format("Deleting Stats with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
