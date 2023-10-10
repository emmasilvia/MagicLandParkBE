package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.CategorieVarsta;
import com.magicland.MagicLandPark.model.Categorie_Tichet;
import com.magicland.MagicLandPark.model.TipAcces;

import java.util.List;

public interface Categorie_TichetService {

    Categorie_Tichet create(Categorie_Tichet categorie_tichet);

    Categorie_Tichet findById(Long id);

    Categorie_Tichet findByName(String nume);

    List<Categorie_Tichet> findAll();
}
