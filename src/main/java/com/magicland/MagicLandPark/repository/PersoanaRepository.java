package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Persoana;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersoanaRepository extends JpaRepository<Persoana, Long> {

    Persoana findByEmail(String email);
}
