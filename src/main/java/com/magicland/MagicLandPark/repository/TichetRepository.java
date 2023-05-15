package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Categorie_Varsta;
import com.magicland.MagicLandPark.model.Rezervare;
import com.magicland.MagicLandPark.model.Tichet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TichetRepository extends JpaRepository<Tichet, Long> {

        Tichet findByCategorieVarsta(Categorie_Varsta categVarsta);
}
