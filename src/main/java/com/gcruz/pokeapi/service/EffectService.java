package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.Effect;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface EffectService {

    List<Effect> findAll() throws Exception;

    Effect findById(long id) throws NotFoundException;

    Effect create(Effect effect) throws Exception;

    void update(Effect effect) throws Exception;

    void delete(long id) throws Exception;
}
