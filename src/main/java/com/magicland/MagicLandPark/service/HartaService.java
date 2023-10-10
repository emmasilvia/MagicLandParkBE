package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Harta;

import java.util.List;

public interface HartaService {

    Harta findByDenumire(String nume);

    List<Harta> findAll();

}
