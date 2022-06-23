package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.entity.Sprite;
import com.gcruz.pokeapi.repository.SpriteRepository;
import com.gcruz.pokeapi.service.SpriteService;
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
class SpriteServiceImplTest {

    @Mock
    private SpriteRepository repository;
    private SpriteService service;

    @BeforeEach
    void setUp() {
        service = new SpriteServiceImpl(repository);
    }

    @Test
    void createSpriteSuccess() throws Exception {
        //given
        Sprite sprite = mockSprite();
        //when
        service.create(sprite);
        //then
        verify(repository).save(sprite);
    }

    @Test
    void searchSpriteSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockSpriteWithId(1L)));
        Sprite sprite = service.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(sprite.getId()).isNotNull();
    }

    @Test
    void searchAllSpritesSuccess() throws Exception {
        //given
        List<Sprite> spritesList = new ArrayList<Sprite>();
        spritesList.add(mockSpriteWithId(1L));
        spritesList.add(mockSpriteWithId(2L));
        //when
        when(repository.findAll()).thenReturn(spritesList);
        //when
        List<Sprite> results = service.findAll();
        //then
        verify(repository).findAll();
        assertThat(results.size()).isEqualTo(spritesList.size());
    }


    @Test
    void updateSpriteSuccess() throws Exception {
        //given
        Sprite newSprite = mockSpriteWithId(1L);
        //when
        service.update(newSprite);
        //then
        verify(repository).save(newSprite);
    }

    @Test
    void deleteSpriteSuccess() throws Exception {
        //when
        service.delete(1L);
        //then
        verify(repository).deleteById(1L);
    }

    Sprite mockSprite() {
        Sprite sprite = new Sprite();
        sprite.setArtWork("dummy.url/pikachu-default");
        sprite.setFrontFemale("dummy.url/pikachu-female");
        return sprite;
    }

    Sprite mockSpriteWithId(long id) {
        Sprite sprite = new Sprite();
        sprite.setId(id);
        sprite.setArtWork("dummy.url/pikachu-default");
        sprite.setFrontFemale("dummy.url/pikachu-female");
        return sprite;
    }
}