package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Harta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HartaRepository extends JpaRepository<Harta, Long> {

    Harta findByDenumire(String nume);
}
