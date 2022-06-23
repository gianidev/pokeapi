package com.gcruz.pokeapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "damage_relations")
public class DamageRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "damage_relation_id")
    private long id;
    @OneToOne(mappedBy = "damageRelation")
    private Type type;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dmg_effectiveness",
            joinColumns = @JoinColumn(name = "damage_relation_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> effectiveAgainst;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dmg_weakness",
            joinColumns = @JoinColumn(name = "damage_relation_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> weakAgainst;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dmg_resistance",
            joinColumns = @JoinColumn(name = "damage_relation_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> resistantTo;

    public DamageRelation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
