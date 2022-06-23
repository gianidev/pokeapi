package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Artwork;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.ArtworkRepository;
import com.gcruz.pokeapi.service.ArtworkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private ArtworkRepository repository;
    private static final Logger logger = LogManager.getLogger(ArtworkServiceImpl.class);

    @Autowired
    public ArtworkServiceImpl(ArtworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Artwork create(Artwork artWork) throws Exception {
        try {
            logger.info("Saving Artworks in database.");
            return repository.save(artWork);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<Artwork> findAll() throws Exception {
        try {
            logger.info("Fetching all Artworks.");
            return (List<Artwork>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all Artworks.");
        }
    }

    @Override
    public Artwork findById(long id) throws NotFoundException {
        Optional<Artwork> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("Artwork with Id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Artwork with Id %s was not found", id));
    }

    @Override
    public Artwork update(Artwork artWork) throws Exception {
        try {
            logger.info(String.format("Updating Artwork with Id %s .", artWork.getId()));
            return repository.save(artWork);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            logger.info(String.format("Deleting Artwork with Id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}