package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.DamageRelation;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface DamageRelationService {
    DamageRelation create(DamageRelation pokemon) throws Exception;

    List<DamageRelation> findAll() throws Exception;

    DamageRelation findById(long id) throws NotFoundException;

    DamageRelation update(DamageRelation pokemon) throws Exception;

    void delete(long id) throws Exception;
}
