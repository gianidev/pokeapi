package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.Sprite;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface SpriteService {
    Sprite create(Sprite sprite) throws Exception;

    List<Sprite> findAll() throws Exception;

    Sprite findById(long id) throws NotFoundException;

    Sprite update(Sprite sprite) throws Exception;

    void delete(long id) throws Exception;
}
