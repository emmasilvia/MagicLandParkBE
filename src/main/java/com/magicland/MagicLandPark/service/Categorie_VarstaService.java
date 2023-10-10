package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.CategorieVarsta;

import java.util.List;

public interface Categorie_VarstaService {

    CategorieVarsta create(CategorieVarsta categorieVarsta);

    CategorieVarsta findByName(String nume);

    List<CategorieVarsta> findAll();

}
