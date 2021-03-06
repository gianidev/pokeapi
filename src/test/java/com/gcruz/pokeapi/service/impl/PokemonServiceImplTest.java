package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.entity.Pokemon;
import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.PokemonRepository;
import com.gcruz.pokeapi.service.GenerationService;
import com.gcruz.pokeapi.service.PokemonService;
import com.gcruz.pokeapi.service.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @Mock
    private PokemonRepository repository;
    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        pokemonService = new PokemonServiceImpl(repository);
    }

    @Test
    void CreatePokemonSuccess() throws Exception {
        //given
        Pokemon pokemon = mockPokemon();
        //when
        pokemonService.create(pokemon);
        //then
        verify(repository).save(pokemon);
    }

    @Test
    void createPokemonFailure() throws Exception {
        //given
        String expectedMessage = "Object contain null values";
        Pokemon pokemon = mockPokemon();
        pokemon.setGeneration(null);
        pokemon.setStats(null);
        //then
        Exception exception = assertThrows(Exception.class, () -> pokemonService.create(pokemon));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void searchPokemonByIdSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockPokemon()));
        Pokemon pokemon = pokemonService.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(pokemon.getId()).isEqualTo(1L);
    }

    @Test
    void searchAllPokemonSuccess() throws Exception {
        //when

        pokemonService.findAll();
        //then
        verify(repository).findAll();
    }


    @Test
    void searchPokemonByNameSuccess() throws NotFoundException {
        //when
        when(repository.findByName(anyString())).thenReturn(java.util.Optional.ofNullable(mockPokemon()));
        Pokemon result = pokemonService.findByName("Pikachu");
        //then
        verify(repository).findByName("Pikachu");
        assertThat(result).isNotNull();
    }

    @Test
    void searchPokemonByNameFailure() throws NotFoundException {
        //given
        String expectedMessage = "Pokemon with name Pikachu not found";
        //when
        when(repository.findByName(anyString())).thenReturn(Optional.empty());
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> pokemonService.findByName("Pikachu"));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updatePokemonSuccess() throws Exception {
        //given
        Pokemon pokemon = mockPokemon();
        //when
        pokemonService.update(pokemon);
        //then
        verify(repository).save(pokemon);
    }

    @Test
    void deletePokemonSuccess() throws Exception {
        //when
        pokemonService.deleteById(1L);
        //then
        verify(repository).deleteById(1L);
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