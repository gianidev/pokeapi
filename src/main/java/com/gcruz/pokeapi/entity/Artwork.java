package com.gcruz.pokeapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "artworks")
@Data
@NoArgsConstructor
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_id")
    private long id;
    private String url;
    @JsonBackReference
    @OneToOne(mappedBy = "artwork")
    private Pokemon pokemon;
}
