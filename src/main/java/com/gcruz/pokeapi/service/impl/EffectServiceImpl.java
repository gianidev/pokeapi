package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Effect;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.EffectRepository;
import com.gcruz.pokeapi.service.EffectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EffectServiceImpl implements EffectService {

    private final EffectRepository repository;

    public EffectServiceImpl(EffectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Effect create(Effect effect) throws Exception {
        try {
            return repository.save(effect);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Effect> findAll() throws Exception {
        try {
            return (List<Effect>) repository.findAll();

        } catch (Exception e) {
            throw new Exception("Error while fetching all effects.");
        }
    }

    @Override
    public Effect findById(long id) throws NotFoundException {
        Optional<Effect> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else throw new NotFoundException(String.format("Effect with id %s not found", id));
    }

    @Override
    public void update(long id, Effect effect) throws Exception {
        try {
            effect.setId(id);
            repository.save(effect);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws Exception {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}