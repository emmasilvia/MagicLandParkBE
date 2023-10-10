package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.Tichet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonRepository extends JpaRepository<Bon, Long> {

    Bon findByNrBon(String nrBon);



    Bon getBonIdByNrBon(String nrBon);
}
