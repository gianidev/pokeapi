package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.entity.Pokemon;
import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.PokemonRepository;
import com.gcruz.pokeapi.service.GenerationService;
import com.gcruz.pokeapi.service.PokemonService;
import com.gcruz.pokeapi.service.StatsService;
import com.gcruz.pokeapi.service.impl.PokemonServiceImpl;
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
class PokemonControllerTest {
    @Mock
    private PokemonService service;
    @Mock
    private PokemonRepository repository;
    @Mock
    private GenerationService generationService;
    @Mock
    private StatsService statsService;
    private PokemonController controller;

    @BeforeEach
    void setUp() {
        service = new PokemonServiceImpl(repository, generationService, statsService);
        controller = new PokemonController(service);
    }

    @Test
    void searchPokemonByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockPokemon()));
        ResponseEntity<Pokemon> response = controller.findById(1L);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Pokemon pokemon = response.getBody();

        assertThat(pokemon.getId()).isEqualTo(1L);
    }

    @Test
    void searchPokemonByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Pokemon with Id 1 was not found";
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createPokemonSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockPokemon());
        ResponseEntity<Pokemon> response = controller.create(mockPokemon());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Pokemon pokemon = response.getBody();

        assertThat(pokemon.getId()).isEqualTo(1L);
    }


    @Test
    void updatePokemonSuccess() throws Exception {
        //when
        ResponseEntity<Pokemon> response = controller.update(mockPokemon());
        //then
        verify(repository).save(any());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void deletePokemonSuccess() throws Exception {//when
        //when
        ResponseEntity<Pokemon> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    Pokemon mockPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("Pikachu");
        pokemon.setHeight(1);
        pokemon.setWeight(1);

        Stats stats = new Stats();
        stats.setAttack(1);
        pokemon.setStats(stats);

        Generation generation = new Generation();
        generation.setId(1L);
        pokemon.setGeneration(generation);

        return pokemon;
    }

}