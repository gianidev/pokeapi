package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.StatsDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.StatsRepository;
import com.gcruz.pokeapi.repository.model.Stats;
import com.gcruz.pokeapi.service.StatsService;
import com.gcruz.pokeapi.service.impl.StatsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsControllerTest {
    @Mock
    private StatsService service;
    @Mock
    private StatsRepository repository;
    private StatsController controller;

    @BeforeEach
    void setUp() {
        service = new StatsServiceImpl(repository);
        controller = new StatsController(new ModelMapper(), service);
    }

    @Test
    void searchStatsByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockStats()));
        ResponseEntity<StatsDTO> response = controller.getStatsByStats(1L);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        StatsDTO stats = response.getBody();

        assertThat(stats.getId()).isEqualTo(1L);
    }

    @Test
    void searchStatsByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Stats with id 1 not found";
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.getStatsByStats(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createStatsSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockStats());
        ResponseEntity<StatsDTO> response = controller.createStats(mockStatsDTO());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        StatsDTO stats = response.getBody();

        assertThat(stats.getId()).isEqualTo(1L);
    }


    @Test
    void updateStatsSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockStats()));
        ResponseEntity<StatsDTO> response = controller.updateStats(mockStatsDTO());
        //then
        verify(repository).save(any());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void deleteStatsSuccess() throws Exception {//when
        //when
        ResponseEntity<Stats> response = controller.deleteStats(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    Stats mockStats() {
        Stats stats = new Stats();
        stats.setId(1L);
        stats.setAttack(10);
        stats.setDefense(5);
        stats.setHealthPoints(100);
        return stats;
    }

    StatsDTO mockStatsDTO() {
        StatsDTO stats = new StatsDTO();
        stats.setId(1L);
        stats.setAttack(10);
        stats.setDefense(5);
        stats.setHealthPoints(100);
        return stats;
    }
}