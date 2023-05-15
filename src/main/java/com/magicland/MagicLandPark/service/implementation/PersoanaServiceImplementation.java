package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.exception.ResursaDejaExistentaInDB;
import com.magicland.MagicLandPark.model.Persoana;
import com.magicland.MagicLandPark.service.PersoanaService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.magicland.MagicLandPark.repository.PersoanaRepository;

@Service
public class PersoanaServiceImplementation implements PersoanaService {

    private PersoanaRepository persoanaRepository;
    private BCryptPasswordEncoder encoder;

    public PersoanaServiceImplementation(PersoanaRepository persoanaRepository){
        this.persoanaRepository = persoanaRepository;
        this.encoder = new BCryptPasswordEncoder();
    }
    @Override
    public Persoana create(Persoana persoana) {

        Persoana persoanaExistInDatabase = persoanaRepository.findByEmail(persoana.getEmail());
        if(persoanaExistInDatabase != null) {
            throw new ResursaDejaExistentaInDB(String.format("Persoana cu emailul %s exista deja in baza de date",persoana.getEmail()));
        }
        persoana.setPassword(encoder.encode(persoana.getPassword()));
        return persoanaRepository.save(persoana);
    }

    @Override
    public Persoana findByEmail(String email) {
        return persoanaRepository.findByEmail(email);
    }
}
