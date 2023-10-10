package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.model.Harta;
import com.magicland.MagicLandPark.repository.HartaRepository;
import com.magicland.MagicLandPark.service.HartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HartaServiceImplementation implements HartaService {

    @Autowired
    private HartaRepository hartaRepository;

    @Autowired
    public HartaServiceImplementation(HartaRepository hartaRepository){
        this.hartaRepository = hartaRepository;
    }
    @Override
    public Harta findByDenumire(String nume) {
        return hartaRepository.findByDenumire(nume);
    }

    @Override
    public List<Harta> findAll() {
        return hartaRepository.findAll();
    }
}
