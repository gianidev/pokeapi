package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.repository.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
