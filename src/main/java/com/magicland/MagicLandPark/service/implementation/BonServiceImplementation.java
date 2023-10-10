package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.CategorieVarsta;
import com.magicland.MagicLandPark.repository.BonRepository;
import com.magicland.MagicLandPark.service.BonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonServiceImplementation implements BonService {

    private BonRepository bonRepository;

    @Autowired
    public BonServiceImplementation(BonRepository bonRepository){
        this.bonRepository = bonRepository;
    }


    @Override
    public Bon create(Bon bon) {
        Bon bonInDB = findByNrBon(bon.getNrBon());
        if(bonInDB != null){
            return bonInDB;
        }
        return bonRepository.save(bon);
    }

    @Override
    public Bon findByNrBon(String nrBon) {
        return bonRepository.findByNrBon(nrBon);
    }

    @Override
    public Bon getBonIdByNrBon(String nrBon) {
        return bonRepository.getBonIdByNrBon(nrBon);
    }

    @Override
    public List<Bon> findAll() {
        return bonRepository.findAll();
    }

    @Override
    public void deleteBon(Long id) {
         bonRepository.deleteById(id);
    }
}
