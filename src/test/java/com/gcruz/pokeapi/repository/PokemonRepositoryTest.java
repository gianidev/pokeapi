package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.entity.Generation;
import com.gcruz.pokeapi.entity.Pokemon;
import com.gcruz.pokeapi.entity.Stats;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PokemonRepositoryTest {
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private GenerationRepository generationRepository;
    @Autowired
    private StatsRepository statsRepository;

    @AfterEach
    void afterEach() {
        pokemonRepository.deleteAll();
        generationRepository.deleteAll();
        statsRepository.deleteAll();
    }

    @Test
    void findByNameSuccess() {
        //given
        Pokemon mockedPokemon = mockPokemon();
        pokemonRepository.save(mockedPokemon);
        //when
        Optional<Pokemon> result = pokemonRepository.findByName("Pikachu");
        //then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(mockedPokemon.getId());
        assertThat(result.get().getName()).isEqualTo(mockedPokemon.getName());
        assertThat(result.get().getHeight()).isEqualTo(mockedPokemon.getHeight());
        assertThat(result.get().getWeight()).isEqualTo(mockedPokemon.getWeight());
    }

    @Test
    void findByNameFailure() {
        //when
        Optional<Pokemon> result = pokemonRepository.findByName("Pikachu");
        //then
        assertThat(result.isPresent()).isFalse();
    }

    Pokemon mockPokemon() {
        Generation generation = new Generation();
        generation.setName("Gen 1");
        generationRepository.save(generation);

        Stats stats = new Stats();
        stats.setAttack(1);
        stats.setDefense(2);
        stats.setHealthPoints(10);
        stats.setSpeed(15);
        stats.setSpeedAttack(23);
        stats.setSpeedDefence(11);
        stats.setTotal(62);
        statsRepository.save(stats);

        Pokemon pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("Pikachu");
        pokemon.setHeight(1);
        pokemon.setWeight(1);
        pokemon.setStats(stats);
        pokemon.setGeneration(generation);
        return pokemon;
    }
}