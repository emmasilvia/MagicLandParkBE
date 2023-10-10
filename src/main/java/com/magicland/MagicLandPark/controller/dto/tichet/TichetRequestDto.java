package com.magicland.MagicLandPark.controller.dto.tichet;

import com.magicland.MagicLandPark.controller.dto.bon.BonRequestDto;
import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.CategorieVarsta;
import com.magicland.MagicLandPark.model.Categorie_Tichet;
import com.magicland.MagicLandPark.model.TipTichet;

import java.time.LocalDateTime;

public class TichetRequestDto {

    private Long nrBilet;

    private Long codAbonament;

    private int durataAbonament;

    private String categorie_tichet;

    private LocalDateTime valabilitate;

    private Bon bon;

    private Long stoc = 1000L;
    private TipTichet tipTichet;

    public Long getCodAbonament() {
        return codAbonament;
    }

    public void setCodAbonament(Long codAbonament) {
        this.codAbonament = codAbonament;
    }

    public int getDurataAbonament() {
        return durataAbonament;
    }

    public void setDurataAbonament(int durataAbonament) {
        this.durataAbonament = durataAbonament;
    }

    public Long getNrBilet() {
        return nrBilet;
    }

    public void setNrBilet(Long nrBilet) {
        this.nrBilet = nrBilet;
    }

    public String getCategorie_tichet() {
        return categorie_tichet;
    }

    public void setCategorie_tichet(String categorie_tichet) {
        this.categorie_tichet = categorie_tichet;
    }

    public LocalDateTime getValabilitate() {
        return valabilitate;
    }

    public void setValabilitate(LocalDateTime valabilitate) {
        this.valabilitate = valabilitate;
    }


    public Bon getBon() {
        return bon;
    }

    public void setBon(Bon bon) {
        this.bon = bon;
    }

    public TipTichet getTipTichet() {
        return tipTichet;
    }

    public void setTipTichet(TipTichet tipTichet) {
        this.tipTichet = tipTichet;
    }

    public Long getStoc() {
        return stoc;
    }

    public void setStoc(Long stoc) {
        this.stoc = stoc;
    }
}
