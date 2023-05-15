package com.magicland.MagicLandPark.service;

import com.magicland.MagicLandPark.model.Activitate_Parc;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface Activitate_ParcService {

    Activitate_Parc create(Activitate_Parc activitate_parc);

    Activitate_Parc findById(Long id);

    Activitate_Parc findByName(String denumire);

    Activitate_Parc update(Activitate_Parc activitate_parc);

    Page<Activitate_Parc> searchActivities(Map<String, String> params);

    void delete(Long id);
}
