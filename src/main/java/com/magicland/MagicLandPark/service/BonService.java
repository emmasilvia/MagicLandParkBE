package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.Tichet;

import java.util.List;

public interface BonService {

    Bon create(Bon bon);

    Bon findByNrBon(String nrBon);

    Bon getBonIdByNrBon(String nrBon);

    List<Bon> findAll();

    void deleteBon(Long id);
}
