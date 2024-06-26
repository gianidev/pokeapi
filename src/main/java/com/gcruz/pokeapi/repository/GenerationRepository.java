package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.model.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long> {
}
