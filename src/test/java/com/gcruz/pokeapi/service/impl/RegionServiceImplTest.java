package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.repository.RegionRepository;
import com.gcruz.pokeapi.repository.model.Region;
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
        Region region = Region.builder().id(1L).name("Kanto").build();
        //when
        service.createRegion(region);
        //then
        verify(repository).save(region);
    }


    @Test
    void searchRegionByIdSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(Region.builder().id(1L).name("Kanto").build()));
        Region region = service.getRegionById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(region.getId()).isEqualTo(1L);
    }

    @Test
    void searchAllRegionSuccess() throws Exception {
        //when
        service.getAllRegions();
        //then
        verify(repository).findAll();
    }

    @Test
    void updateRegionSuccess() throws Exception {
        //given
        Region region = Region.builder().id(1L).name("Kanto").build();
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(region));
        service.updateRegion(region);
        //then
        verify(repository).save(region);
    }

    @Test
    void deleteRegionByIdSuccess() throws Exception {
        //when
        service.deleteRegion(1L);
        //then
        verify(repository).deleteById(1L);
    }

}
