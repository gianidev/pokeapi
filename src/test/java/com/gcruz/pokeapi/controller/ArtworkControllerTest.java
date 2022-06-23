package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Sprite;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.SpriteRepository;
import com.gcruz.pokeapi.service.SpriteService;
import com.gcruz.pokeapi.service.impl.SpriteServiceImpl;
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
class SpriteControllerTest {

    @Mock
    private SpriteService service;
    @Mock
    private SpriteRepository repository;
    private SpriteController controller;

    @BeforeEach
    void setUp() {
        service = new SpriteServiceImpl(repository);
        controller = new SpriteController(service);
    }

    @Test
    void searchSpriteByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockSpriteWithId(1L)));
        ResponseEntity<Sprite> response = controller.findById(1L);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Sprite sprite = response.getBody();
        assertThat(sprite.getId()).isEqualTo(1L);
    }

    @Test
    void searchSpriteByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Sprite with Id 1 was not found";

        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));

        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));

        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createSpriteSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockSpriteWithId(1L));
        ResponseEntity<Sprite> response = controller.create(mockSprite());

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Sprite sprite = response.getBody();
        assertThat(sprite.getId()).isEqualTo(1L);
    }


    @Test
    void updateSpriteSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockSpriteWithId(1L));
        ResponseEntity<Sprite> response = controller.update(mockSpriteWithId(1L));

        //then
        verify(repository).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }


    @Test
    void deleteSpriteSuccess() throws Exception {//when
        //when
        ResponseEntity<Sprite> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
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