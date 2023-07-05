package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Artwork;

import java.util.List;

public interface ArtworkService {
    Artwork createArtwork(Artwork artwork) throws Exception;

    List<Artwork> getAllArtworks() throws Exception;

    Artwork getArtworkById(long id) throws NotFoundException;

    Artwork updateArtwork(Artwork artwork) throws Exception;

    void deleteArtwork(long id) throws NotFoundException;
}
