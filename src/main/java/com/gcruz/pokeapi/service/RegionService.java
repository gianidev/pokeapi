package com.gcruz.pokeapi.service;

import com.gcruz.pokeapi.repository.model.Region;
import com.gcruz.pokeapi.exception.NotFoundException;

import java.util.List;

public interface RegionService {
    List<Region> getAllRegions() throws Exception;

    Region getRegionById(long id) throws NotFoundException;

    Region createRegion(Region region) throws Exception;

    Region updateRegion(Region region) throws Exception;

    void deleteRegion(long id) throws Exception;
}
