package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.DamageRelation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.DamageRelationRepository;
import com.gcruz.pokeapi.service.DamageRelationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DamageRelationServiceImpl implements DamageRelationService {
    private final DamageRelationRepository repository;
    private static final Logger logger = LogManager.getLogger(DamageRelationServiceImpl.class);

    @Autowired
    public DamageRelationServiceImpl(DamageRelationRepository repository) {
        this.repository = repository;
    }

    @Override
    public DamageRelation create(DamageRelation damageRelation) throws Exception {
        try {
            logger.info("Saving DamageRelation in database.");
            return repository.save(damageRelation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<DamageRelation> findAll() throws Exception {
        try {
            logger.info("Fetching all DamageRelation.");
            return (List<DamageRelation>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all damageRelation.");
        }
    }

    @Override
    public DamageRelation findById(long id) throws NotFoundException {
        Optional<DamageRelation> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info(String.format("DamageRelation with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("DamageRelation with id %s not found", id));
    }


    @Override
    public DamageRelation update(DamageRelation damageRelation) throws Exception {
        try {
            logger.info(String.format("Updating DamageRelation with id %s .", damageRelation.getId()));
            return repository.save(damageRelation);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            logger.info(String.format("Deleting DamageRelation with id %s.", id));
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
