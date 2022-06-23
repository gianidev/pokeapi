package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Artwork;
import com.gcruz.pokeapi.repository.ArtworkRepository;
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
        Artwork artWork = mockArtwork();
        //when
        service.create(artWork);
        //then
        verify(repository).save(artWork);
    }

    @Test
    void searchArtworkSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockArtworkWithId(1L)));
        Artwork artWork = service.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(artWork.getId()).isNotNull();
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
        Artwork newArtwork = mockArtworkWithId(1L);
        //when
        service.update(newArtwork);
        //then
        verify(repository).save(newArtwork);
    }

    @Test
    void deleteArtworkSuccess() throws Exception {
        //when
        service.delete(1L);
        //then
        verify(repository).deleteById(1L);
    }

    Artwork mockArtwork() {
        Artwork artWork = new Artwork();
        artWork.setArtWork("dummy.url/pikachu-default");
        return artWork;
    }

    Artwork mockArtworkWithId(long id) {
        Artwork artWork = new Artwork();
        artWork.setId(id);
        artWork.setArtWork("dummy.url/pikachu-default");
        return artWork;
    }
}