package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TipRepository extends JpaRepository<Tip, Long> {

    Tip findByNume(String nume);
}
