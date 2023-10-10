package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.TipAcces;

import java.util.List;

public interface TipAccesService {

    TipAcces create(TipAcces tipAcces);

    TipAcces findByName(String nume);

    List<TipAcces> findAll();
}
