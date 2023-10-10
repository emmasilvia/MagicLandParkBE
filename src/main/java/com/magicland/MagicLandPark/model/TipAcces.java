package com.magicland.MagicLandPark.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipAcces {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String nume;

    public TipAcces(){}

    public TipAcces(String nume) {
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
