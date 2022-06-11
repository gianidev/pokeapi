package com.gcruz.pokeapi.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "stats")
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int healthPoints;
    @NotNull
    private int attack;
    @NotNull
    private int defense;
    @NotNull
    private int speedAttack;
    @NotNull
    private int speedDefence;
    @NotNull
    private int speed;
    @NotNull
    private int total;

    public Stats() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeedAttack() {
        return speedAttack;
    }

    public void setSpeedAttack(int speedAttack) {
        this.speedAttack = speedAttack;
    }

    public int getSpeedDefence() {
        return speedDefence;
    }

    public void setSpeedDefence(int speedDefence) {
        this.speedDefence = speedDefence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
