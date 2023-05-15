package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Persoana;
import org.springframework.stereotype.Service;


public interface PersoanaService {

    Persoana create(Persoana persoana);
    Persoana findByEmail(String email);
}
