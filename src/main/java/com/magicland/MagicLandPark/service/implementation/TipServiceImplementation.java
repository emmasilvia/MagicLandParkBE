package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.model.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import com.magicland.MagicLandPark.repository.TipRepository;
import com.magicland.MagicLandPark.service.TipService;

import java.util.List;

@Service
public class TipServiceImplementation implements TipService {

    private TipRepository tipRepository;

    @Autowired
    public TipServiceImplementation(TipRepository tipRepository){
        this.tipRepository = tipRepository;
    }

    @Override
    public Tip create(Tip tip) {
        Tip tipInDB = findByName(tip.getNume());
        if(tipInDB != null){
            return tipInDB;
        }
        return tipRepository.save(tip);
    }

    @Override
    public Tip findByName(String nume) {
        return tipRepository.findByNume(nume);
    }

    @Override
    public List<Tip> findAll() {
        return tipRepository.findAll();
    }
}
