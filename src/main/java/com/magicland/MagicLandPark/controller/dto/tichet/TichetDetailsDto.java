package com.magicland.MagicLandPark.controller.dto.tichet;

import com.magicland.MagicLandPark.model.Categorie_Tichet;
import com.magicland.MagicLandPark.model.Tichet;
import com.magicland.MagicLandPark.service.Categorie_TichetService;

public class TichetDetailsDto {

    private Tichet tichet;
    private Categorie_Tichet categorie_tichet;

    public Tichet getTichet() {
        return tichet;
    }

    public void setTichet(Tichet tichet) {
        this.tichet = tichet;
    }

    public Categorie_Tichet getCategorie_tichet() {
        return categorie_tichet;
    }

    public void setCategorie_tichet(Categorie_Tichet categorie_tichet) {
        this.categorie_tichet = categorie_tichet;
    }
}
