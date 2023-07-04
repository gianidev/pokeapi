package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.repository.ArtworkRepository;
import com.gcruz.pokeapi.repository.model.Artwork;
import com.gcruz.pokeapi.service.ArtworkService;
import com.gcruz.pokeapi.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtworkServiceImplTest {

    @Mock
    private ArtworkRepository repository;
    @Mock
    private PokemonService pokemonService;
    private ArtworkService service;

    @BeforeEach
    void setUp() {
        service = new ArtworkServiceImpl(repository,pokemonService);
    }

    @Test
    void createArtworkSuccess() throws Exception {
        //given
        Artwork artwork = mockArtwork();
        //when
        service.createArtwork(artwork);
        //then
        verify(repository).save(artwork);
    }

    @Test
    void searchArtworkSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockArtworkWithId(1L)));
        Artwork artwork = service.getArtworkById(1L);
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
        List<Artwork> results = service.getAllArtworks();
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
        service.updateArtwork(artwork);
        //then
        verify(repository).save(artwork);
    }

    @Test
    void deleteArtworkSuccess() throws Exception {
        //when
        when(repository.findById(1L)).thenReturn(Optional.of(mockArtworkWithId(1L)));
        service.deleteArtwork(1L);
        //assert
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