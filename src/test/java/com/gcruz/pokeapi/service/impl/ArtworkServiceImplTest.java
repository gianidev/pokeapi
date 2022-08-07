package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.repository.ArtworkRepository;
import com.gcruz.pokeapi.repository.model.Artwork;
import com.gcruz.pokeapi.service.ArtworkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtworkServiceImplTest {

    @Mock
    private ArtworkRepository repository;
    private ArtworkService service;

    @BeforeEach
    void setUp() {
        service = new ArtworkServiceImpl(repository);
    }

    @Test
    void createArtworkSuccess() throws Exception {
        //given
        Artwork artwork = mockArtwork();
        //when
        service.create(artwork);
        //then
        verify(repository).save(artwork);
    }

    @Test
    void searchArtworkSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockArtworkWithId(1L)));
        Artwork artwork = service.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(artwork.getId()).isNotNull();
    }

    @Test
    void searchAllArtworksSuccess() throws Exception {
        //given
        List<Artwork> spritesList = new ArrayList<Artwork>();
        spritesList.add(mockArtworkWithId(1L));
        spritesList.add(mockArtworkWithId(2L));
        //when
        when(repository.findAll()).thenReturn(spritesList);
        //when
        List<Artwork> results = service.findAll();
        //then
        verify(repository).findAll();
        assertThat(results.size()).isEqualTo(spritesList.size());
    }


    @Test
    void updateArtworkSuccess() throws Exception {
        //given
        Artwork artwork = mockArtworkWithId(1L);
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(artwork));
        service.update(artwork);
        //then
        verify(repository).save(artwork);
    }

    @Test
    void deleteArtworkSuccess() throws Exception {
        //when
        service.delete(1L);
        //then
        verify(repository).deleteById(1L);
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