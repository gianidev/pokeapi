package com.gcruz.pokeapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "damageRelation")
public class DamageRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Type noDamageTo;
    @OneToOne
    private Type HalfDamageTo;
    @OneToOne
    private Type DoubleDamageTo;
    @OneToOne
    private Type HalfDamageFrom;
    @OneToOne
    private Type noDamageFrom;
    @OneToOne
    private Type DoubleDamageFrom;

    public DamageRelation(long id, Type noDamageTo, Type halfDamageTo, Type doubleDamageTo, Type halfDamageFrom,
                          Type noDamageFrom, Type doubleDamageFrom) {
        this.id = id;
        this.noDamageTo = noDamageTo;
        HalfDamageTo = halfDamageTo;
        DoubleDamageTo = doubleDamageTo;
        HalfDamageFrom = halfDamageFrom;
        this.noDamageFrom = noDamageFrom;
        DoubleDamageFrom = doubleDamageFrom;
    }

    public Type getNoDamageTo() {
        return noDamageTo;
    }

    public void setNoDamageTo(Type noDamageTo) {
        this.noDamageTo = noDamageTo;
    }

    public Type getHalfDamageTo() {
        return HalfDamageTo;
    }

    public void setHalfDamageTo(Type halfDamageTo) {
        HalfDamageTo = halfDamageTo;
    }

    public Type getDoubleDamageTo() {
        return DoubleDamageTo;
    }

    public void setDoubleDamageTo(Type doubleDamageTo) {
        DoubleDamageTo = doubleDamageTo;
    }

    public Type getHalfDamageFrom() {
        return HalfDamageFrom;
    }

    public void setHalfDamageFrom(Type halfDamageFrom) {
        HalfDamageFrom = halfDamageFrom;
    }

    public Type getNoDamageFrom() {
        return noDamageFrom;
    }

    public void setNoDamageFrom(Type noDamageFrom) {
        this.noDamageFrom = noDamageFrom;
    }

    public Type getDoubleDamageFrom() {
        return DoubleDamageFrom;
    }

    public void setDoubleDamageFrom(Type doubleDamageFrom) {
        DoubleDamageFrom = doubleDamageFrom;
    }
}
