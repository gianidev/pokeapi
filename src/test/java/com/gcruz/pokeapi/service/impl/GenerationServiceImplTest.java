package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.repository.model.Generation;
import com.gcruz.pokeapi.repository.GenerationRepository;
import com.gcruz.pokeapi.service.GenerationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenerationServiceImplTest {

    @Mock
    private GenerationRepository repository;
    private GenerationService service;

    @BeforeEach
    void setUp() {
        service = new GenerationServiceImpl(repository);
    }

    @Test
    void saveGenerationSuccess() throws Exception {
        //given
        Generation generation = mockGeneration();
        //when
        service.createGeneration(generation);
        //then
        verify(repository).save(generation);
    }


    @Test
    void searchGenerationByIdSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockGeneration()));
        Generation generation = service.getGenerationById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(generation.getId()).isEqualTo(1L);
    }

    @Test
    void searchAllGenerationSuccess() throws Exception {
        //when
        service.getAllGenerations();
        //then
        verify(repository).findAll();
    }

    @Test
    void updateGenerationSuccess() throws Exception {
        //given
        Generation generation = mockGeneration();
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(generation));
        service.updateGeneration(generation);
        //then
        verify(repository).save(generation);
    }

    @Test
    void deleteGenerationSuccess() throws Exception {
        //when
        service.deleteGeneration(1L);
        //then
        verify(repository).deleteById(1L);
    }

    Generation mockGeneration() {
        Generation generation = new Generation();
        generation.setId(1L);
        generation.setName("Generation I");
        return generation;
    }
}