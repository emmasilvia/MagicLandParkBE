package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.controller.dto.rezervare.RezervareResponseDto;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RezervareServiceImplementation implements RezervareService {

    private RezervareRepository rezervareRepository;

    private PersoanaRepository persoanaRepository;

    private TichetRepository tichetRepository;

    private BonRepository bonRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RezervareServiceImplementation(RezervareRepository rezervareRepository, PersoanaRepository persoanaRepository, TichetRepository tichetRepository, BonRepository bonRepository) {
        this.rezervareRepository = rezervareRepository;
        this.persoanaRepository = persoanaRepository;
        this.tichetRepository = tichetRepository;
        this.bonRepository = bonRepository;
    }

//    @Override
//    public Rezervare createReservation(Rezervare rezervare) throws InsufficientResourcesException {
//        Bon bon = new Bon();
//        bon.setNrBon("BON-" + UUID.randomUUID());
//        bon.setDataBon(LocalDateTime.now());
//        Bon createdBon = bonRepository.save(bon); // Save the Bon entity
//
//        rezervare.setBon(createdBon);
//
//        Rezervare rezervareInDB = findByDataRezervare(rezervare.getDataRezervare());
//        if(rezervareInDB != null){
//            return rezervareInDB;
//        }
//        return rezervareRepository.save(rezervare);
//
//    }

    @Override
    public Rezervare createReservation(Rezervare rezervare) throws InsufficientResourcesException {
        Bon bon = new Bon();
        bon.setNrBon("BON-" + UUID.randomUUID());
        bon.setDataBon(LocalDateTime.now());
        Bon createdBon = bonRepository.save(bon); // Save the Bon entity

        rezervare.setBon(createdBon);

        Rezervare rezervareInDB = findByDataRezervare(rezervare.getDataRezervare());
        if (rezervareInDB != null) {
            return rezervareInDB;
        }
        Rezervare createdRezervare = rezervareRepository.save(rezervare);


        return createdRezervare;
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

    //todo
    @Override
    public Rezervare findByDataRezervare(LocalDateTime dataRezervare) {
        return rezervareRepository.findByDataRezervare(dataRezervare);
    }


    @Override
    public List<Rezervare> findAllReservationsByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rezervare> query = cb.createQuery(Rezervare.class);
        Root<Rezervare> rezervareRoot = query.from(Rezervare.class);
        Join<Rezervare, Persoana> rezervarePersoanaJoin = rezervareRoot.join("persoana");

        query.select(rezervareRoot)
                .where(cb.equal(rezervarePersoanaJoin.get("email"), email));

        TypedQuery<Rezervare> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public Rezervare findByNrBon(String nrBon) {
        Bon bon = bonRepository.findByNrBon(nrBon);
        if (bon != null) {
            return rezervareRepository.findByBon(bon);
        }
        return null;
    }


    @Override
    public List<Rezervare> getAllReservations() {
        return rezervareRepository.findAll();
    }



    @Override
    public void deleteRezervare(Long id) {
        rezervareRepository.deleteById(id);
    }


    @Override
    public Optional<Rezervare> findByIdOptional(Long id) {
        return rezervareRepository.findById(id);
    }

    @Override
    public List<Rezervare> findAll() {
        return rezervareRepository.findAll();
    }
}
