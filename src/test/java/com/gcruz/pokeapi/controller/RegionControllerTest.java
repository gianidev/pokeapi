package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.ArtworkDTO;
import com.gcruz.pokeapi.dto.RegionDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.RegionRepository;
import com.gcruz.pokeapi.repository.model.Region;
import com.gcruz.pokeapi.service.RegionService;
import com.gcruz.pokeapi.service.impl.RegionServiceImpl;
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
class RegionControllerTest {
    @Mock
    private RegionService service;
    @Mock
    private RegionRepository repository;
    private RegionController controller;

    @BeforeEach
    void setUp() {
        service = new RegionServiceImpl(repository);
        controller = new RegionController(new ModelMapper(), service);
    }

    @Test
    void searchRegionByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockRegion()));
        ResponseEntity<RegionDTO> response = controller.findById(1L);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        RegionDTO region = response.getBody();

        assertThat(region.getId()).isEqualTo(1L);
    }

    @Test
    void searchRegionByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Region with id 1 not found";
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createRegionSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockRegion());
        ResponseEntity<RegionDTO> response = controller.create(mockRegionDTO());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        RegionDTO region = response.getBody();

        assertThat(region.getId()).isEqualTo(1L);
    }


    @Test
    void updateRegionSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockRegion()));
        ResponseEntity<RegionDTO> response = controller.update(mockRegionDTO());
        //then
        verify(repository).save(any());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void deleteRegionSuccess() throws Exception {
        //when
        ResponseEntity<Region> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    Region mockRegion() {
        return Region.builder().id(1L).name("Kanto").build();
    }

    RegionDTO mockRegionDTO() {
        RegionDTO dto = new RegionDTO();
        dto.setId(1L);
        dto.setName("Kanto");
        return dto;
    }
}