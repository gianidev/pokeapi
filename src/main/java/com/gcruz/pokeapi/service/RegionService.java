package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.entity.Region;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface RegionService {
    List<Region> findAll() throws Exception;

    Region findById(long id) throws NotFoundException;

    Region create(Region region) throws Exception;

    Region update(Region region) throws Exception;

    void delete(long id) throws Exception;
}
