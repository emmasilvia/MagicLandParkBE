package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.Rezervare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RezervareRepository extends JpaRepository<Rezervare, Long> {

    Rezervare findByDataRezervare(LocalDateTime dataRezervare);
    List<Rezervare> findAllByPersoanaEmail(String email);

    Rezervare findByBon(Bon bon);




}
