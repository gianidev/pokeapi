package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.entity.DamageRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageRelationRepository extends JpaRepository<DamageRelation, Long> {
}
