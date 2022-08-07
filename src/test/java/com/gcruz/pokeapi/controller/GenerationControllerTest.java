package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.repository.model.Generation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.GenerationRepository;
import com.gcruz.pokeapi.service.GenerationService;
import com.gcruz.pokeapi.service.impl.GenerationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenerationControllerTest {
    @Mock
    private GenerationService service;
    @Mock
    private GenerationRepository repository;
    private GenerationController controller;

    @BeforeEach
    void setUp() {
        service = new GenerationServiceImpl(repository);
        controller = new GenerationController(service);
    }

    @Test
    void searchGenerationByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockGeneration()));
        ResponseEntity<Generation> response = controller.findById(1L);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Generation generation = response.getBody();

        assertThat(generation.getName()).isEqualTo("Generation I");
    }

    @Test
    void searchGenerationByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Generation with id 1 was not found";
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createGenerationSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockGeneration());
        ResponseEntity<Generation> response = controller.create(mockGeneration());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Generation generation = response.getBody();

        assertThat(generation.getName()).isEqualTo("Generation I");
    }


    @Test
    void updateGenerationSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockGeneration()));
        ResponseEntity<Generation> response = controller.update(mockGeneration());
        //then
        verify(repository).save(any());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void deleteGenerationSuccess() throws Exception {//when
        //when
        ResponseEntity<Generation> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    Generation mockGeneration() {
        Generation generation = new Generation();
        generation.setId(1L);
        generation.setName("Generation I");
        return generation;
    }
}