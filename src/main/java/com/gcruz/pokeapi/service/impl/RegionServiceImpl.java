package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Region;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.RegionRepository;
import com.gcruz.pokeapi.service.RegionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;
    private static final Logger logger = LogManager.getLogger(RegionServiceImpl.class);

    @Autowired
    public RegionServiceImpl(RegionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Region create(Region region) throws Exception {
        try {
            logger.info("Saving Region in DB.");
            return repository.save(region);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Region> findAll() throws Exception {
        try {
            logger.info("Fetching all Regions.");
            return (List<Region>) repository.findAll();

        } catch (Exception e) {
            throw new Exception("Error while fetching all regions.");
        }
    }

    @Override
    public Region findById(long id) throws NotFoundException {
        Optional<Region> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("Region with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Region with id %s not found", id));
    }

    @Override
    public Region update(Region region) throws Exception {
        try {
            logger.info(String.format("Updating Region with id %s .", region.getId()));
            return repository.save(region);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            logger.info(String.format("Deleting Region with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}