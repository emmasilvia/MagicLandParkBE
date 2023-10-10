package com.magicland.MagicLandPark.model;

import javax.persistence.*;
import java.io.File;
import java.util.List;
import java.util.Set;

@Entity(name = "activitati_parc")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

    @Enumerated(value = EnumType.STRING)
    private NivelDificultate nivelDificultate;

    @Enumerated(value = EnumType.STRING)
    private TipActivitate tipActivitate;

    private String imagine;

    @OneToMany
    private List<Rezervare> rezervari;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "activitati_zone", joinColumns = @JoinColumn(name = "activitate_id"), inverseJoinColumns = @JoinColumn(name = "harta_id"))
    private Set<Harta> zonaHarta;


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

    public NivelDificultate getNivelDificultate() {
        return nivelDificultate;
    }

    public void setNivelDificultate(NivelDificultate nivelDificultate) {
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

    public Set<Harta> getZonaHarta() {
        return zonaHarta;
    }

    public void setZonaHarta(Set<Harta> zonaHarta) {
        this.zonaHarta = zonaHarta;
    }

    public List<Animal> getAnimale() {
        return animale;
    }

    public void setAnimale(List<Animal> animale) {
        this.animale = animale;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }
}
