package com.magicland.MagicLandPark.model;

import javax.persistence.*;

@Entity(name = "animale_zoo")
public class Animal {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nume;

    @Column
    private String rasa;

    @Column
    private int varsta;

    @Column
    private String descriere;

    @ManyToOne
    private Activitate_Parc activitate_parc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
