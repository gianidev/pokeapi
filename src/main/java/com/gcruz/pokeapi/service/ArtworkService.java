package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.Artwork;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface ArtworkService {
    Artwork create(Artwork artWork) throws Exception;

    List<Artwork> findAll() throws Exception;

    Artwork findById(long id) throws NotFoundException;

    Artwork update(Artwork artWork) throws Exception;

    void delete(long id) throws Exception;
}
