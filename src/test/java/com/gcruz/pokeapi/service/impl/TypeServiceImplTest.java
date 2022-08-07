package com.gcruz.pokeapi.service.impl;

import com.gcruz.pokeapi.repository.model.Type;
import com.gcruz.pokeapi.repository.TypeRepository;
import com.gcruz.pokeapi.service.TypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypeServiceImplTest {

    @Mock
    private TypeRepository repository;
    private TypeService service;

    @BeforeEach
    void setUp() {
        service = new TypeServiceImpl(repository);
    }

    @Test
    void createTypeSuccess() throws Exception {
        //given
        Type type = mockType();
        //when
        service.create(type);
        //then
        verify(repository).save(type);
    }


    @Test
    void seachTypeByIdSuccess() throws Exception {
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockType()));
        Type type = service.findById(1L);
        //then
        verify(repository).findById(1L);
        assertThat(type.getId()).isEqualTo(1L);
    }

    @Test
    void searchAllTypeSuccess() throws Exception {
        //when
        service.findAll();
        //then
        verify(repository).findAll();
    }

    @Test
    void updateTypeSuccess() throws Exception {
        //given
        Type type = mockType();
        //when
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(type));
        service.update(type);
        //then
        verify(repository).save(type);
    }

    @Test
    void deleteTypeByIdSuccess() throws Exception {
        //when
        service.delete(1L);
        //then
        verify(repository).deleteById(1L);
    }

    Type mockType() {
        Type type = new Type();
        type.setId(1L);
        type.setName("Fire");
        return type;
    }
}
