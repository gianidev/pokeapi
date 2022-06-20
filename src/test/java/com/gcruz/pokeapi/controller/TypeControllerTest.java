package com.gcruz.pokeapi.controller;

import com.gcruz.pokeapi.entity.Type;
import com.gcruz.pokeapi.exception.NotFoundException;
import com.gcruz.pokeapi.repository.TypeRepository;
import com.gcruz.pokeapi.service.TypeService;
import com.gcruz.pokeapi.service.impl.TypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypeControllerTest {

    @Mock
    private TypeService service;
    @Mock
    private TypeRepository repository;
    private TypeController controller;

    @BeforeEach
    void setUp() {
        service = new TypeServiceImpl(repository);
        controller = new TypeController(service);
    }

    @Test
    void searchTypeByIdSuccess() throws NotFoundException {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockType()));
        ResponseEntity<Type> response = controller.findById(1L);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Type type = response.getBody();

        assertThat(type.getId()).isEqualTo(1L);
    }

    @Test
    void searchTypeByIdFails() throws NotFoundException {
        //given
        String expectedMessage = "Type with id 1 not found";
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        Exception exception = assertThrows(NotFoundException.class, () -> controller.findById(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void createTypeSuccess() throws Exception {
        //when
        when(repository.save(any())).thenReturn(mockType());
        ResponseEntity<Type> response = controller.create(mockType());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Type type = response.getBody();

        assertThat(type.getId()).isEqualTo(1L);
    }


    @Test
    void updateTypeSuccess() throws Exception {
        //when
        ResponseEntity<Type> response = controller.update(mockType());
        //then
        verify(repository).save(any());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void deleteTypeSuccess() throws Exception {//when
        //when
        ResponseEntity<Type> response = controller.delete(1L);
        //then
        verify(repository).deleteById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    Type mockType() {
        Type type = new Type();
        type.setId(1L);
        type.setName("Fire");
        return type;
    }
}
