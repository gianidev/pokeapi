package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.RegionRepository;
import com.gcruz.pokeapi.repository.model.Region;
import com.gcruz.pokeapi.service.RegionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;

    @Override
    public Region create(Region region) throws Exception {
        try {
            log.info("Saving Region in DB.");
            return repository.save(region);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Region> findAll() throws Exception {
        try {
            log.info("Fetching all Regions.");
            return (List<Region>) repository.findAll();

        } catch (Exception e) {
            throw new Exception("Error while fetching all regions.");
        }
    }

    @Override
    public Region findById(long id) throws NotFoundException {
        Optional<Region> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Region with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Region with id %s not found", id));
    }

    @Override
    public Region update(Region region) throws Exception {
        try {
            log.info(String.format("Updating Region with id %s .", region.getId()));
            repository.save(region);
            return findById(region.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            log.info(String.format("Deleting Region with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}