package com.magicland.MagicLandPark.controller.dto.rezervare;

import com.magicland.MagicLandPark.controller.dto.bon.BonRequestDto;
import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.Persoana;
import com.magicland.MagicLandPark.model.Tichet;

import java.time.LocalDateTime;
import java.util.List;

public class RezervareRequestDto {

    private Long id;

    private LocalDateTime dataRezervare;
    private LocalDateTime dataVizita;

    private String persoana;

    private int nrPersoane;

    private BonRequestDto bon;

    private Long activitateId;

    public LocalDateTime getDataRezervare() {
        return dataRezervare;
    }

    public void setDataRezervare(LocalDateTime dataRezervare) {
        this.dataRezervare = dataRezervare;
    }

    public LocalDateTime getDataVizita() {
        return dataVizita;
    }

    public void setDataVizita(LocalDateTime dataVizita) {
        this.dataVizita = dataVizita;
    }


    public String getPersoana() {
        return persoana;
    }

    public void setPersoana(String persoana) {
        this.persoana = persoana;
    }

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public BonRequestDto getBon() {
        return bon;
    }

    public void setBon(BonRequestDto bon) {
        this.bon = bon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivitateId() {
        return activitateId;
    }

    public void setActivitateId(Long activitateId) {
        this.activitateId = activitateId;
    }
}
