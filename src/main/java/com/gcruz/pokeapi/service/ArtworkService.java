package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.model.Artwork;

import java.util.List;

public interface ArtworkService {
    Artwork create(Artwork artwork) throws Exception;

    List<Artwork> findAll() throws Exception;

    Artwork findById(long id) throws NotFoundException;

    Artwork update(Artwork artwork) throws Exception;

    void delete(long id) throws Exception;
}
