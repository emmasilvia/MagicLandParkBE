package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Rezervare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezervareRepository extends JpaRepository<Rezervare, Long> {

    List<Rezervare> findAllByPersoanaId(Long id);
}
