package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.DamageRelation;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.DamageRelationRepository;
import com.gcruz.pokeapi.service.DamageRelationService;
import com.gcruz.pokeapi.service.impl.DamageRelationServiceImpl;
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
class DamageRelationControllerTest {

    @Mock
    private DamageRelationService service;
    @Mock
    private DamageRelationRepository repository;
    private DamageRelationController controller;

    @BeforeEach
    void setUp() {
        service = new DamageRelationServiceImpl(repository);
        controller = new DamageRelationController(service);
    }

    @Test
    void searchDamageRelationByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockDamageRelation()));
        ResponseEntity<DamageRelation> response = controller.findById(1L);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DamageRelation damageRelation = response.getBody();

        assertThat(damageRelation.getId()).isEqualTo(1L);
    }

    @Test
    void searchDamageRelationByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "DamageRelation with id 1 not found";
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createDamageRelationSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockDamageRelation());
        ResponseEntity<DamageRelation> response = controller.create(mockDamageRelation());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DamageRelation damageRelation = response.getBody();

        assertThat(damageRelation.getId()).isEqualTo(1L);
    }


    @Test
    void updateDamageRelationSuccess() throws Exception {
        //when
        ResponseEntity<DamageRelation> response = controller.update(mockDamageRelation());
        //then
        verify(repository).save(any());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void deleteDamageRelationSuccess() throws Exception {//when
        //when
        ResponseEntity<DamageRelation> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    DamageRelation mockDamageRelation() {
        DamageRelation damageRelation = new DamageRelation();
        damageRelation.setId(1L);
        damageRelation.setResistantTo(null);
        return damageRelation;
    }

}
