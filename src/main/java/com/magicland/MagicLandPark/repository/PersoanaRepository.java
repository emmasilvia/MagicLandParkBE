package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Persoana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersoanaRepository extends JpaRepository<Persoana, Long> {

    Persoana findByEmail(String email);

    Persoana findByNume(String nume);
}
