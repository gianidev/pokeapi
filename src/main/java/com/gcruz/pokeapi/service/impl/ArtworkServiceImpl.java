package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Sprite;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.SpriteRepository;
import com.gcruz.pokeapi.service.SpriteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpriteServiceImpl implements SpriteService {

    private SpriteRepository repository;
    private static final Logger logger = LogManager.getLogger(SpriteServiceImpl.class);

    @Autowired
    public SpriteServiceImpl(SpriteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sprite create(Sprite sprite) throws Exception {
        try {
            logger.info("Saving Sprites in database.");
            return repository.save(sprite);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<Sprite> findAll() throws Exception {
        try {
            logger.info("Fetching all Sprites.");
            return (List<Sprite>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all Sprites.");
        }
    }

    @Override
    public Sprite findById(long id) throws NotFoundException {
        Optional<Sprite> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("Sprite with Id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Sprite with Id %s was not found", id));
    }

    @Override
    public Sprite update(Sprite sprite) throws Exception {
        try {
            logger.info(String.format("Updating Sprite with Id %s .", sprite.getId()));
            return repository.save(sprite);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            logger.info(String.format("Deleting Sprite with Id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}