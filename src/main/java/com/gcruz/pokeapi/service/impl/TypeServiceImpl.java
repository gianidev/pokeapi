package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.TypeRepository;
import com.gcruz.pokeapi.model.Type;
import com.gcruz.pokeapi.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TypeServiceImpl implements TypeService {

    private final TypeRepository repository;

    @Override
    public Type createType(Type type) throws Exception {
        try {
            log.info("Saving Type in database.");
            return repository.save(type);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Type> getAllTypes() throws Exception {
        try {
            log.info("Fetching all Type.");
            return (List<Type>) repository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while fetching all type.");
        }
    }

    @Override
    public Type getTypeById(long id) throws NotFoundException {
        Optional<Type> optional = repository.findById(id);
        if (optional.isPresent()) {
            log.info(String.format("Type with id %s has been found.", id));
            return optional.get();
        } else throw new NotFoundException(String.format("Type with id %s not found", id));
    }


    @Override
    public Type updateType(Type type) throws Exception {
        try {
            log.info(String.format("Updating Type with id %s .", type.getId()));
            repository.save(type);
            return getTypeById(type.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteType(long id) throws NotFoundException {
        try {
            log.info(String.format("Deleting Type with id %s.", id));
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format("Can't delete, Type with Id %s does not exist", id));
        }
    }
}

