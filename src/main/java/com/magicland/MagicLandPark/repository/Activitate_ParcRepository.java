package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.Harta;
import com.magicland.MagicLandPark.model.TipAccesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Activitate_ParcRepository extends JpaRepository<Activitate_Parc, Long> , JpaSpecificationExecutor<Activitate_Parc> {

    Optional<Activitate_Parc> findByDenumire(String denumire);

    List<Activitate_Parc> findByZonaHarta(Harta harta);



}
