package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.CategorieVarsta;
import com.magicland.MagicLandPark.model.Categorie_Tichet;
import com.magicland.MagicLandPark.model.Rezervare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Categorie_TichetRepository extends JpaRepository<Categorie_Tichet, Long> {

    Optional<Categorie_Tichet> findByCategorieVarsta(String nume);
}
