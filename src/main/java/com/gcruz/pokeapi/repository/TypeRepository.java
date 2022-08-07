package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.repository.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
