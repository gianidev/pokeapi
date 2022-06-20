package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Region;
import com.gcruz.pokeapi.repository.RegionRepository;
import com.gcruz.pokeapi.service.RegionService;
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
class RegionServiceImplTest {

    @Mock
    private RegionRepository repository;
    private RegionService service;

    @BeforeEach
    void setUp() {
        service = new RegionServiceImpl(repository);
    }

    @Test
    void createRegionSuccess() throws Exception {
        //given
        Region region = new Region(1L, "Kanto");
        //when
        service.create(region);
        //then
        verify(repository).save(region);
    }


    @Test
    void seachRegionByIdSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(new Region(1L, "Kanto")));
        Region region = service.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(region.getId()).isEqualTo(1L);
    }

    @Test
    void searchAllRegionSuccess() throws Exception {
        //when
        service.findAll();
        //then
        verify(repository).findAll();
    }

    @Test
    void updateRegionSuccess() throws Exception {
        //given
        Region region = new Region(1L, "Kanto");
        //when
        service.update(region);
        //then
        verify(repository).save(region);
    }

    @Test
    void deleteRegionByIdSuccess() throws Exception {
        //when
        service.delete(1L);
        //then
        verify(repository).deleteById(1L);
    }

}
