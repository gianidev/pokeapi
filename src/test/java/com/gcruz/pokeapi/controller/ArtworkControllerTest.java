package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.dto.ArtworkDTO;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.ArtworkRepository;
import com.gcruz.pokeapi.repository.model.Artwork;
import com.gcruz.pokeapi.service.ArtworkService;
import com.gcruz.pokeapi.service.impl.ArtworkServiceImpl;
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
class ArtworkControllerTest {

    @Mock
    private ArtworkService service;
    @Mock
    private ArtworkRepository repository;
    private ArtworkController controller;

    @BeforeEach
    void setUp() {
        service = new ArtworkServiceImpl(repository);
        controller = new ArtworkController(new ModelMapper(), service);
    }

    @Test
    void searchArtworkByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockArtwork()));
        ResponseEntity<ArtworkDTO> response = controller.getArtworkById(1L);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ArtworkDTO artwork = response.getBody();
        assertThat(artwork.getId()).isEqualTo(1L);
    }

    @Test
    void searchArtworkByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Artwork with Id 1 was not found";

        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));

        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.getArtworkById(1L));

        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createArtworkSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockArtwork());
        ResponseEntity<ArtworkDTO> response = controller.createArtwork(mockArtworkDTO());

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ArtworkDTO artworkDTO = response.getBody();
        assertThat(artworkDTO.getId()).isEqualTo(1L);
    }


    @Test
    void updateArtworkSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockArtwork()));
        ResponseEntity<ArtworkDTO> response = controller.updateArtwork(mockArtworkDTO());

        //then
        verify(repository).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }


    @Test
    void deleteArtworkSuccess() throws Exception {
        //when
        ResponseEntity<ArtworkDTO> response = controller.deleteArtwork(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    Artwork mockArtwork() {
        Artwork artwork = new Artwork();
        artwork.setId(1L);
        artwork.setUrl("dummy.url/pikachu-default");
        return artwork;
    }

    ArtworkDTO mockArtworkDTO() {
        ArtworkDTO artwork = new ArtworkDTO();
        artwork.setId(1L);
        artwork.setUrl("dummy.url/pikachu-default");
        return artwork;
    }
}