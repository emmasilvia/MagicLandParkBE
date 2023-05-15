package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.TipActivitate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Activitate_ParcRepository extends JpaRepository<Activitate_Parc, Long> , JpaSpecificationExecutor<Activitate_Parc> {

    Optional<Activitate_Parc> findByDenumire(String denumire);

    Activitate_Parc findByTipActivitate(TipActivitate tipActivitate);

}
