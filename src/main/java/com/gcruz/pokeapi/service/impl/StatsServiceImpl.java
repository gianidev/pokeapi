package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.StatsRepository;
import com.gcruz.pokeapi.service.StatsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {

    private final StatsRepository repository;
    private static final Logger logger = LogManager.getLogger(StatsServiceImpl.class);

    @Autowired
    public StatsServiceImpl(StatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stats create(Stats stats) throws Exception {
        try {
            logger.info("Saving Stats in database.");
            return repository.save(stats);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Stats> findAll() throws Exception {
        try {
            logger.info("Fetching all Stats.");
            return (List<Stats>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all stats.");
        }
    }

    @Override
    public Stats findById(long id) throws NotFoundException {
        Optional<Stats> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("Stats with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Stats with id %s not found", id));
    }


    @Override
    public void update(Stats stats) throws Exception {
        try {
            logger.info(String.format("Updating Stats with id %s .", stats.getId()));
            repository.save(stats);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            logger.info(String.format("Deleting Stats with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
