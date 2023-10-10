package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.TipAcces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipAccesRepository extends JpaRepository<TipAcces, Long> {

        TipAcces findByNume(String nume);
}
