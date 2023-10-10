package com.magicland.MagicLandPark.controller.dto.activitate_parc;

import com.magicland.MagicLandPark.model.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.File;
import java.util.List;

public class ActivitateRequestDto {

    private String denumire;

    private String descriere;

    private int durata;

    private int varstaMinima;

    private String program;

    private NivelDificultate nivelDificultate;

    private TipActivitate tipActivitate;

    private String imagine;

    private String zonaHarta;

    private List<Animal> animale;

    private List<Rezervare> rezervari;


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


    public String getZonaHarta() {
        return zonaHarta;
    }

    public void setZonaHarta(String zonaHarta) {
        this.zonaHarta = zonaHarta;
    }

    public List<Animal> getAnimale() {
        return animale;
    }

    public void setAnimale(List<Animal> animale) {
        this.animale = animale;
    }

    public List<Rezervare> getRezervari() {
        return rezervari;
    }

    public void setRezervari(List<Rezervare> rezervari) {
        this.rezervari = rezervari;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }
}
