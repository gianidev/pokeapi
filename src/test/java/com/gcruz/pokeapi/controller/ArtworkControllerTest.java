package com.gcruz.pokeapi.controller;

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
        controller = new ArtworkController(service);
    }

    @Test
    void searchArtworkByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockArtworkWithId(1L)));
        ResponseEntity<Artwork> response = controller.findById(1L);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Artwork artwork = response.getBody();
        assertThat(artwork.getId()).isEqualTo(1L);
    }

    @Test
    void searchArtworkByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Artwork with Id 1 was not found";

        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));

        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));

        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createArtworkSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockArtworkWithId(1L));
        ResponseEntity<Artwork> response = controller.create(mockArtwork());

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Artwork artwork = response.getBody();
        assertThat(artwork.getId()).isEqualTo(1L);
    }


    @Test
    void updateArtworkSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockArtworkWithId(1L)));
        ResponseEntity<Artwork> response = controller.update(mockArtworkWithId(1L));

        //then
        verify(repository).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }


    @Test
    void deleteArtworkSuccess() throws Exception {//when
        //when
        ResponseEntity<Artwork> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    Artwork mockArtwork() {
        Artwork artwork = new Artwork();
        artwork.setUrl("dummy.url/pikachu-default");
        return artwork;
    }

    Artwork mockArtworkWithId(long id) {
        Artwork artwork = new Artwork();
        artwork.setId(id);
        artwork.setUrl("dummy.url/pikachu-default");
        return artwork;
    }
}