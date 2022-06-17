package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Stats;
import com.gcruz.pokeapi.repository.StatsRepository;
import com.gcruz.pokeapi.service.StatsService;
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
class StatsServiceImplTest {

    @Mock
    private StatsRepository repository;
    private StatsService service;

    @BeforeEach
    void setUp() {
        service = new StatsServiceImpl(repository);
    }

    @Test
    void createStatsSuccess() throws Exception {
        //given
        Stats stats = mockStats();
        //when
        service.create(stats);
        //then
        verify(repository).save(stats);
    }


    @Test
    void seachStatsByIdSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockStats()));
        Stats stats = service.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(stats.getId()).isEqualTo(1L);
    }

    @Test
    void searchAllStatsSuccess() throws Exception {
        //when
        service.findAll();
        //then
        verify(repository).findAll();
    }

    @Test
    void updateStatsSuccess() throws Exception {
        //given
        Stats stats = mockStats();
        //when
        service.update(stats);
        //then
        verify(repository).save(stats);
    }

    @Test
    void deleteStatsByIdSuccess() throws Exception {
        //when
        service.deleteById(1L);
        //then
        verify(repository).deleteById(1L);
    }

    Stats mockStats() {
        Stats stats = new Stats();
        stats.setId(1L);
        stats.setAttack(10);
        stats.setDefense(5);
        stats.setHealthPoints(100);
        return stats;
    }
}