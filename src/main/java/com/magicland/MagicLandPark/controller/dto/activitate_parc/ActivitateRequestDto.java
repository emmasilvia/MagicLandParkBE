package com.magicland.MagicLandPark.controller.dto.activitate_parc;

import com.magicland.MagicLandPark.model.Animal;
import com.magicland.MagicLandPark.model.Rezervare;
import com.magicland.MagicLandPark.model.TipActivitate;
import com.magicland.MagicLandPark.model.Zona;

import java.util.List;

public class ActivitateRequestDto {

    private String denumire;

    private String descriere;

    private int durata;

    private int varstaMinima;

    private String program;

    private String nivelDificultate;

    private TipActivitate tipActivitate;

    private List<Rezervare> rezervari;

    private List<Zona> zone;

    private List<Animal> animale;


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
