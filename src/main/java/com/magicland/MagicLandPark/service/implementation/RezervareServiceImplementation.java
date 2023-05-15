package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.*;
import com.magicland.MagicLandPark.repository.BonRepository;
import com.magicland.MagicLandPark.repository.PersoanaRepository;
import com.magicland.MagicLandPark.repository.RezervareRepository;
import com.magicland.MagicLandPark.repository.TichetRepository;
import com.magicland.MagicLandPark.service.RezervareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Service
public class RezervareServiceImplementation implements RezervareService {

    private RezervareRepository rezervareRepository;

    private PersoanaRepository persoanaRepository;

    private TichetRepository tichetRepository;

    private BonRepository bonRepository;

    @Autowired
    public RezervareServiceImplementation(RezervareRepository rezervareRepository, PersoanaRepository persoanaRepository, TichetRepository tichetRepository, BonRepository bonRepository) {
        this.rezervareRepository = rezervareRepository;
        this.persoanaRepository = persoanaRepository;
        this.tichetRepository = tichetRepository;
        this.bonRepository = bonRepository;
    }





/*    În metoda de creare a rezervării,se scade cantitatea din stocul tichetelor
      în funcție de numărul de persoane din rezervare.
      Prin urmare, trebuie găsite tichetele potrivite pentru a putea actualiza stocul
      În acest caz, se caută tichetele după tipul și tipul accesului pentru că,
      în cadrul aplicației tale, un anumit tip de tichet poate fi utilizat
      pentru mai multe activități.
      De exemplu, un tichet de o zi poate fi folosit pentru orice activitate
      din parc, nu doar pentru o activitate specifică.
      Prin urmare, căutarea tichetelor pe baza tipului și tipului accesului
      este mai relevantă pentru a reduce cantitatea din stocul potrivit. */

//    @Override
//    public Rezervare createReservation(Rezervare rezervare, Bon bon) throws InsufficientResourcesException {
////        Authentication user = SecurityContextHolder.getContext().getAuthentication();
////        Persoana persoana = persoanaRepository.findByEmail(user.getName());
//
//        Tichet tichet = tichetRepository.findByCategorieVarsta(rezervare.getCategorie_varsta());
//        if (tichet == null) {
//            throw new ResursaNegasitaInDB("Tichetul nu a fost gasit.");
//        }
//
//        int stocDisponibil = tichet.getStoc() - tichet.getStocRezervat();
//        if (stocDisponibil < rezervare.getNrPersoane()) {
//            throw new InsufficientResourcesException("Nu exista suficient stoc disponibil.");
//        }
//
//        tichet.setStocRezervat(tichet.getStocRezervat() + rezervare.getNrPersoane());
//        tichetRepository.save(tichet);
//
////        rezervare.setPersoana(persoana);
//        Rezervare rezervareSalvata = rezervareRepository.save(rezervare);
//
//        Random random = new Random();
//        bon.setNrBon(random.nextInt(1_000_000_000));
//        bon.setDataBon(Date.from(Instant.now()));
//        bon.setRezervari(Collections.singletonList(rezervareSalvata));
//        bon.setTichete(Collections.singletonList(tichet));
//        bonRepository.save(bon);
//
//        return rezervareSalvata;
//    }


    @Override
    public Rezervare createReservation(Rezervare rezervare) throws InsufficientResourcesException {
        return rezervareRepository.save(rezervare);

    }

    @Override
    public Rezervare findById(Long id) {
        Optional<Rezervare> optionalRezervare = rezervareRepository.findById(id);
        if(optionalRezervare.isPresent()) {
            return optionalRezervare.get();
        }else{
            throw new ResursaNegasitaInDB(String.format("Rezervarea cu id %d nu exista", id));
        }
    }

    @Override
    public List<Rezervare> findAllReservationsByUserId(Long id) {
        return rezervareRepository.findAllByPersoanaId(id);
    }

    @Override
    public void deleteRezervare(Long id) {
        rezervareRepository.deleteById(id);
    }





}
