package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.Harta;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface Activitate_ParcService {

    Activitate_Parc create(Activitate_Parc activitate_parc);

    Activitate_Parc findById(Long id);

    Activitate_Parc findByName(String denumire);

    Activitate_Parc update(Activitate_Parc activitate_parc);

    Page<Activitate_Parc> searchActivities(Map<String, String> params);

//    List<Activitate_Parc> findAll();

    void delete(Long id);

    List<Activitate_Parc> findAll();
    List<Activitate_Parc> findByZonaHarta(Harta harta);
}
