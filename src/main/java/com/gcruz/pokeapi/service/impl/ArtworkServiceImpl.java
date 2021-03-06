package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Artwork;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.ArtworkRepository;
import com.gcruz.pokeapi.service.ArtworkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ArtworkServiceImpl implements ArtworkService {

    private ArtworkRepository repository;

    @Override
    public Artwork create(Artwork artWork) throws Exception {
        try {
            log.info("Saving Artworks in database.");
            return repository.save(artWork);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<Artwork> findAll() throws Exception {
        try {
            log.info("Fetching all Artworks.");
            return (List<Artwork>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all Artworks.");
        }
    }

    @Override
    public Artwork findById(long id) throws NotFoundException {
        Optional<Artwork> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Artwork with Id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Artwork with Id %s was not found", id));
    }

    @Override
    public Artwork update(Artwork artWork) throws Exception {
        try {
            log.info(String.format("Updating Artwork with Id %s .", artWork.getId()));
            return repository.save(artWork);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            log.info(String.format("Deleting Artwork with Id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}