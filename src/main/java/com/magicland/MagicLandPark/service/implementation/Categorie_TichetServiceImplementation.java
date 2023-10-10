package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.*;
import com.magicland.MagicLandPark.repository.Categorie_TichetRepository;
import com.magicland.MagicLandPark.service.Categorie_TichetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Categorie_TichetServiceImplementation implements Categorie_TichetService {

    @Autowired
    private Categorie_TichetRepository categorie_tichetRepository;

    @Autowired
    public Categorie_TichetServiceImplementation(Categorie_TichetRepository categorie_tichetRepository) {
        this.categorie_tichetRepository = categorie_tichetRepository;
    }

    public Categorie_TichetServiceImplementation(){}

    @Override
    public Categorie_Tichet create(Categorie_Tichet categorie_tichet) {
        return categorie_tichetRepository.save(categorie_tichet);
    }

    @Override
    public Categorie_Tichet findById(Long id) {
        Optional<Categorie_Tichet> categorie_tichet = categorie_tichetRepository.findById(id);
        if(categorie_tichet.isPresent()) {
            return categorie_tichet.get();
        }else {
            throw  new ResursaNegasitaInDB(String.format("Categoria cu id-ul %d nu s-a gasit.", id));
        }
    }

    @Override
    public Categorie_Tichet findByName(String nume) {
        Optional<Categorie_Tichet> optionalCategorie_tichet = categorie_tichetRepository.findByCategorieVarsta(nume);
        if(optionalCategorie_tichet.isPresent()){
            return optionalCategorie_tichet.get();
        }else{
            throw new ResursaNegasitaInDB(String.format("Categorie tichet cu numele %s nu exista",nume));
        }
    }

    @Override
    public List<Categorie_Tichet> findAll() {
        return categorie_tichetRepository.findAll();
    }

}
