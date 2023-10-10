package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.CategorieVarsta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categorie_VarstaRepository extends JpaRepository<CategorieVarsta, Long> {

    CategorieVarsta findByNume(String nume);
}
