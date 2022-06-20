package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.DamageRelation;
import com.gcruz.pokeapi.entity.Type;
import com.gcruz.pokeapi.repository.DamageRelationRepository;
import com.gcruz.pokeapi.service.DamageRelationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DamageRelationServiceImplTest {

    @Mock
    private DamageRelationRepository repository;
    private DamageRelationService service;

    @BeforeEach
    void setUp() {
        service = new DamageRelationServiceImpl(repository);
    }

    @Test
    void createDamageRelationSuccess() throws Exception {
        //given
        DamageRelation damageRelation = mockDamageRelation();
        //when
        service.create(damageRelation);
        //then
        verify(repository).save(damageRelation);
    }


    @Test
    void seachDamageRelationByIdSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockDamageRelation()));
        DamageRelation damageRelation = service.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(damageRelation.getId()).isEqualTo(1L);
        assertThat(damageRelation.getEffectiveAgainst()).isNull();
        assertThat(damageRelation.getWeakAgainst()).isNotNull();
    }

    @Test
    void searchAllDamageRelationSuccess() throws Exception {
        //when
        service.findAll();
        //then
        verify(repository).findAll();
    }

    @Test
    void updateDamageRelationSuccess() throws Exception {
        //given
        DamageRelation damageRelation = mockDamageRelation();
        //when
        service.update(damageRelation);
        //then
        verify(repository).save(damageRelation);
    }

    @Test
    void deleteDamageRelationByIdSuccess() throws Exception {
        //when
        service.delete(1L);
        //then
        verify(repository).deleteById(1L);
    }

    DamageRelation mockDamageRelation() {
        Type typeGhost = new Type();
        typeGhost.setName("Ghost");
        List<Type> weakList = new ArrayList<Type>();
        weakList.add(typeGhost);


        DamageRelation damageRelation = new DamageRelation();
        damageRelation.setId(1L);
        damageRelation.setEffectiveAgainst(null);
        damageRelation.setWeakAgainst(weakList);
        return damageRelation;
    }
}
