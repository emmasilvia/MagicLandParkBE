package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Tip;

import java.util.List;

public interface TipService {

    Tip create(Tip tip);
    Tip findByName(String nume);
    List<Tip> findAll();
}
