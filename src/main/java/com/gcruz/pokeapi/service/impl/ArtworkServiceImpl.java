package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.ArtworkRepository;
import com.gcruz.pokeapi.repository.model.Artwork;
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
    public Artwork create(Artwork artwork) throws Exception {
        try {
            log.info("Saving Artworks in database.");
            return repository.save(artwork);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<Artwork> getAllArtworks() throws Exception {
        try {
            log.info("Fetching all Artworks.");
            return (List<Artwork>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all Artworks.");
        }
    }

    @Override
    public Artwork getArtworkById(long id) throws NotFoundException {
        Optional<Artwork> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Artwork with Id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Artwork with Id %s was not found", id));
    }

    @Override
    public Artwork updateArtwork(Artwork artwork) throws Exception {
        try {
            log.info(String.format("Updating Artwork with Id %s .", artwork.getId()));
            repository.save(artwork);
            return getArtworkById(artwork.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteArtwork(long id) throws Exception {
        try {
            log.info(String.format("Deleting Artwork with Id %s.", id));
            if (!associatedToPokemon(id)) {
                repository.deleteById(id);
            } else {
                throw new Exception("Artwork associated to Pokemon, can't be deleted");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean associatedToPokemon(long id) throws NotFoundException {
        Artwork artwork = getArtworkById(id);
        return artwork.getPokemon() != null;
    }
}