package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.entity.Pokemon;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PokemonRepositoryTest {
    @Autowired
    private PokemonRepository repository;

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @Test
    void itShouldFindPokemonByNameTest() {
        //given
        Pokemon mockedPokemon = mockPokemon();
        repository.save(mockedPokemon);
        //when
        Optional<Pokemon> result = repository.findByName("Pikachu");
        //then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(mockedPokemon.getId());
        assertThat(result.get().getName()).isEqualTo(mockedPokemon.getName());
        assertThat(result.get().getHeight()).isEqualTo(mockedPokemon.getHeight());
        assertThat(result.get().getWeight()).isEqualTo(mockedPokemon.getWeight());
    }

    @Test
    void itShouldNotFindPokemonByNameTest() {
        //when
        Optional<Pokemon> result = repository.findByName("Pikachu");
        //then
        assertThat(result.isPresent()).isFalse();
    }

    Pokemon mockPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("Pikachu");
        pokemon.setHeight(1);
        pokemon.setWeight(1);
        return pokemon;
    }
}