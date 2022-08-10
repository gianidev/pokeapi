package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.repository.model.Type;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface TypeService {
    Type createType(Type stats) throws Exception;

    List<Type> getAllTypes() throws Exception;

    Type getTypeById(long id) throws NotFoundException;

    Type updateType(Type stats) throws Exception;

    void deleteType(long id) throws Exception;
}
