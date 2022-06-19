package com.gcruz.pokeapi.repository;

import com.gcruz.pokeapi.entity.Sprite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpriteRepository extends JpaRepository<Sprite, Long> {
}
