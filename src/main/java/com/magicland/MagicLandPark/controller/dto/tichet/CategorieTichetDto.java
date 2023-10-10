package com.magicland.MagicLandPark.controller.dto.tichet;

import com.magicland.MagicLandPark.model.*;

public class CategorieTichetDto {

    private Long id;
    private String categorieVarsta;

    private double tarif;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorieVarsta() {
        return categorieVarsta;
    }

    public void setCategorieVarsta(String categorieVarsta) {
        this.categorieVarsta = categorieVarsta;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
}
