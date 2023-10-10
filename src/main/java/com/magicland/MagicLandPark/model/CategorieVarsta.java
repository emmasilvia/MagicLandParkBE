package com.magicland.MagicLandPark.model;

import javax.persistence.*;

@Entity
@Table
public class CategorieVarsta {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String nume;

    public CategorieVarsta(){}

    public CategorieVarsta(String nume) {
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
