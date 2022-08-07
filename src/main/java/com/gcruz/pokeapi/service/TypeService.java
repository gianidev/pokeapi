package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.repository.model.Type;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface TypeService {
    Type create(Type stats) throws Exception;

    List<Type> findAll() throws Exception;

    Type findById(long id) throws NotFoundException;

    Type update(Type stats) throws Exception;

    void delete(long id) throws Exception;
}
