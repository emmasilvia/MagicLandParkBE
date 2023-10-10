package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.model.Tip;
import com.magicland.MagicLandPark.model.TipAcces;
import com.magicland.MagicLandPark.repository.TipAccesRepository;
import com.magicland.MagicLandPark.service.TipAccesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipAccesServiceImplementation implements TipAccesService {

    private TipAccesRepository tipAccesRepository;

    @Autowired
    public TipAccesServiceImplementation(TipAccesRepository tipAccesRepository){
        this.tipAccesRepository = tipAccesRepository;
    }


    @Override
    public TipAcces create(TipAcces tipAcces) {
        TipAcces tipAccesInDB = findByName(tipAcces.getNume());
        if(tipAccesInDB != null){
            return tipAccesInDB;
        }
        return tipAccesRepository.save(tipAcces);
    }

    @Override
    public TipAcces findByName(String nume) {
        return tipAccesRepository.findByNume(nume);
    }

    @Override
    public List<TipAcces> findAll() {
        return tipAccesRepository.findAll();
    }
}
