package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.model.CategorieVarsta;
import com.magicland.MagicLandPark.repository.Categorie_VarstaRepository;
import com.magicland.MagicLandPark.service.Categorie_VarstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Categorie_VarstaServiceImplementation implements Categorie_VarstaService {

   private Categorie_VarstaRepository categorie_varstaRepository;

   @Autowired
   public Categorie_VarstaServiceImplementation(Categorie_VarstaRepository categorie_varstaRepository){
       this.categorie_varstaRepository = categorie_varstaRepository;
   }


    @Override
    public CategorieVarsta create(CategorieVarsta categorieVarsta) {
        CategorieVarsta categorieVarstaInDB = findByName(categorieVarsta.getNume());
        if(categorieVarstaInDB != null){
            return categorieVarstaInDB;
        }
        return categorie_varstaRepository.save(categorieVarsta);
    }

    @Override
    public CategorieVarsta findByName(String nume) {
        return categorie_varstaRepository.findByNume(nume);
    }

    @Override
    public List<CategorieVarsta> findAll() {
        return categorie_varstaRepository.findAll();
    }

}
