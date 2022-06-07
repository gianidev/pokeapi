package com.gcruz.pokeapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "sprite")
public class Sprite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fontDefault;
    private String frontFemale;

    public String getFontDefault() {
        return fontDefault;
    }

    public void setFontDefault(String fontDefault) {
        this.fontDefault = fontDefault;
    }

    public String getFrontFemale() {
        return frontFemale;
    }

    public void setFrontFemale(String frontFemale) {
        this.frontFemale = frontFemale;
    }
}
