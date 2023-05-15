package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "activitati_parc")
public class Activitate_Parc {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String denumire;

    @Column(nullable = false)
    private String descriere;

    @Column
    private int durata;

    @Column(nullable = false)
    private int varstaMinima;

    @Column(nullable = false)
    private String program;

    @Column
    private String nivelDificultate;

    @Column(nullable = false)
    private TipActivitate tipActivitate;


    @OneToMany
    private List<Rezervare> rezervari;


    @OneToMany
    private List<Zona> zone;


    @OneToMany
    private List<Animal> animale;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public int getVarstaMinima() {
        return varstaMinima;
    }

    public void setVarstaMinima(int varstaMinima) {
        this.varstaMinima = varstaMinima;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getNivelDificultate() {
        return nivelDificultate;
    }

    public void setNivelDificultate(String nivelDificultate) {
        this.nivelDificultate = nivelDificultate;
    }

    public TipActivitate getTipActivitate() {
        return tipActivitate;
    }

    public void setTipActivitate(TipActivitate tipActivitate) {
        this.tipActivitate = tipActivitate;
    }

    public List<Rezervare> getRezervari() {
        return rezervari;
    }

    public void setRezervari(List<Rezervare> rezervari) {
        this.rezervari = rezervari;
    }

    public List<Zona> getZone() {
        return zone;
    }

    public void setZone(List<Zona> zone) {
        this.zone = zone;
    }

    public List<Animal> getAnimale() {
        return animale;
    }

    public void setAnimale(List<Animal> animale) {
        this.animale = animale;
    }
}
