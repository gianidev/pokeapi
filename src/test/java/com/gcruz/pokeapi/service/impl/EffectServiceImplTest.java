package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Effect;
import com.gcruz.pokeapi.repository.EffectRepository;
import com.gcruz.pokeapi.service.EffectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EffectServiceImplTest {

    @Mock
    private EffectRepository repository;
    private EffectService service;

    @BeforeEach
    void setUp() {
        service = new EffectServiceImpl(repository);
    }

    @Test
    void createEffectSuccess() throws Exception {
        //given
        Effect effect = mockEffect();
        //when
        service.create(effect);
        //then
        verify(repository).save(effect);
    }

    @Test
    void searchEffectSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockEffect()));
        Effect effect = service.findById(1L);
        //then
        verify(repository).findById(1L);
    }

    @Test
    void searchAllEffectsSuccess() throws Exception {
        //when
        service.findAll();
        //then
        verify(repository).findAll();
    }


    @Test
    void updateEffectSuccess() throws Exception {
        //given
        Effect effect = mockEffect();
        //when
        service.update(effect);
        //then
        verify(repository).save(effect);
    }

    @Test
    void deleteEffectSuccess() throws Exception {
        //when
        service.delete(1L);
        //then
        verify(repository).deleteById(1L);
    }

    Effect mockEffect() {
        Effect effect = new Effect();
        effect.setEffect("Used in battle : Catches a wild Pokémon without fail. If used in a trainer battle, nothing happens and the ball is lost.");
        effect.setShortEffect("Catches a wild Pokémon every time.");
        return effect;
    }

}