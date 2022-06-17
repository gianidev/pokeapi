package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Effect;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.EffectRepository;
import com.gcruz.pokeapi.service.EffectService;
import com.gcruz.pokeapi.service.impl.EffectServiceImpl;
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
class EffectControllerTest {

    @Mock
    private EffectService service;
    @Mock
    private EffectRepository repository;
    private EffectController controller;

    @BeforeEach
    void setUp() {
        service = new EffectServiceImpl(repository);
        controller = new EffectController(service);
    }

    @Test
    void searchEffectByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockEffect()));
        ResponseEntity<Effect> response = controller.findById(1L);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Effect effect = response.getBody();

        assertThat(effect.getId()).isEqualTo(1L);
    }

    @Test
    void searchEffectByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Effect with id 1 not found";
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createEffectSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockEffect());
        ResponseEntity<Effect> response = controller.create(mockEffect());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Effect effect = response.getBody();

        assertThat(effect.getId()).isEqualTo(1L);
    }


    @Test
    void updateEffectSuccess() throws Exception {
        //when
        ResponseEntity<Effect> response = controller.update(mockEffect());
        //then
        verify(repository).save(any());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void deleteEffectSuccess() throws Exception {//when
        //when
        ResponseEntity<Effect> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    Effect mockEffect() {
        Effect effect = new Effect();
        effect.setId(1L);
        effect.setEffect("Used in battle : Catches a wild Pokémon without fail. If used in a trainer battle, nothing happens and the ball is lost.");
        effect.setShortEffect("Catches a wild Pokémon every time.");
        return effect;
    }
}
