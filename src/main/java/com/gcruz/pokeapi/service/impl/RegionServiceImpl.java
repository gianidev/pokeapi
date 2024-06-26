package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.RegionRepository;
import com.gcruz.pokeapi.model.Region;
import com.gcruz.pokeapi.service.RegionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;

    @Override
    public Region createRegion(Region region) throws Exception {
        try {
            log.info("Saving Region in DB.");
            return repository.save(region);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Region> getAllRegions() throws Exception {
        try {
            log.info("Fetching all Regions.");
            return (List<Region>) repository.findAll();

        } catch (Exception e) {
            throw new Exception("Error while fetching all regions.");
        }
    }

    @Override
    public Region getRegionById(long id) throws NotFoundException {
        Optional<Region> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Region with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Region with id %s not found", id));
    }

    @Override
    public Region updateRegion(Region region) throws Exception {
        try {
            log.info(String.format("Updating Region with id %s .", region.getId()));
            repository.save(region);
            return getRegionById(region.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteRegion(long id) throws NotFoundException {
        try {
            log.info(String.format("Deleting Region with id %s.", id));
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format("Can't delete, Region with Id %s does not exist", id));
        }
    }

}