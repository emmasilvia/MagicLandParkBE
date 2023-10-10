package com.magicland.MagicLandPark.service;


import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.Categorie_Tichet;
import com.magicland.MagicLandPark.model.Tichet;
import com.magicland.MagicLandPark.model.TipAcces;
import com.magicland.MagicLandPark.repository.TichetRepository;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface TichetService {

    List<Tichet> findTicheteByBon(Long bonId);

    Tichet findByName(String nume);

    List<Tichet> findAll();

    List<Tichet> getTicheteWithCategorii();

    public void storeTicheteForToken(String token, List<Tichet> tichete);

    public List<Tichet> getTicheteByToken(String token);
    Tichet findTichetByBonId(Long id);

    Long getStocDisponibil(Long id);

    List<Tichet> findByStocGreaterThan(Long stoc);

    void delete(List<Tichet> tichete);

    List<Tichet> create(String nrBon, List<Tichet> tichete) throws InsufficientResourcesException;

}
