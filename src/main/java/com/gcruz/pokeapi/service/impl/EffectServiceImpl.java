package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Effect;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.EffectRepository;
import com.gcruz.pokeapi.service.EffectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EffectServiceImpl implements EffectService {

    private final EffectRepository repository;
    private static final Logger logger = LogManager.getLogger(EffectServiceImpl.class);

    @Autowired
    public EffectServiceImpl(EffectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Effect create(Effect effect) throws Exception {
        try {
            logger.info("Saving Effect in DB.");
            return repository.save(effect);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Effect> findAll() throws Exception {
        try {
            logger.info("Fetching all Effects.");
            return (List<Effect>) repository.findAll();

        } catch (Exception e) {
            throw new Exception("Error while fetching all effects.");
        }
    }

    @Override
    public Effect findById(long id) throws NotFoundException {
        Optional<Effect> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("Effect with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Effect with id %s not found", id));
    }

    @Override
    public void update(long id, Effect effect) throws Exception {
        try {
            logger.info(String.format("Updating Effect with id %s .", id));

            effect.setId(id);
            repository.save(effect);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            logger.info(String.format("Deleting Effect with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}