package com.gcruz.pokeapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "damageRelation")
public class DamageRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Type> effectiveAgainst;
    @OneToMany
    private List<Type> weakAgainst;
    @OneToMany
    private List<Type> resistantTo;

    public DamageRelation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Type> getEffectiveAgainst() {
        return effectiveAgainst;
    }

    public void setEffectiveAgainst(List<Type> effectiveAgainst) {
        this.effectiveAgainst = effectiveAgainst;
    }

    public List<Type> getWeakAgainst() {
        return weakAgainst;
    }

    public void setWeakAgainst(List<Type> weakAgainst) {
        this.weakAgainst = weakAgainst;
    }

    public List<Type> getResistantTo() {
        return resistantTo;
    }

    public void setResistantTo(List<Type> resistantTo) {
        this.resistantTo = resistantTo;
    }
}
