package com.magicland.MagicLandPark.service.implementation;
import com.magicland.MagicLandPark.model.*;
import com.magicland.MagicLandPark.repository.BonRepository;
import com.magicland.MagicLandPark.repository.Categorie_TichetRepository;
import com.magicland.MagicLandPark.repository.RezervareRepository;
import com.magicland.MagicLandPark.repository.TichetRepository;
import com.magicland.MagicLandPark.service.TichetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class TichetServiceImplementation implements TichetService {

    @Autowired
    private TichetRepository tichetRepository;

    @Autowired
    private BonRepository bonRepository;

    @Autowired
    private RezervareRepository rezervareRepository;

    @Autowired
    private Categorie_TichetRepository categorie_tichetRepository;

    @PersistenceContext
    private EntityManager entityManager;


    private Long lastBiletCode = 0L;
    private Long lastAbonamentCode = 0L;

    private final Map<String, List<Tichet>> ticheteTemporare = new HashMap<>();

    @Autowired
    public TichetServiceImplementation(TichetRepository tichetRepository, BonRepository bonRepository, RezervareRepository rezervareRepository, Categorie_TichetRepository categorie_tichetRepository) {
        this.tichetRepository = tichetRepository;
        this.bonRepository = bonRepository;
        this.rezervareRepository = rezervareRepository;
        this.categorie_tichetRepository = categorie_tichetRepository;
    }






//    @Override
//    public Tichet findByName(String nume) {
//        Optional<Tichet> optionalTichet = tichetRepository.findByDenumire(nume);
//        if(optionalTichet.isPresent()){
//            return optionalTichet.get();
//        }else{
//            throw new ResursaNegasitaInDB(String.format("Tichet cu numele %s nu exista",nume));
//        }
//    }

    @Override
    public List<Tichet> findTicheteByBon(Long bonId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tichet> query = cb.createQuery(Tichet.class);
        Root<Tichet> tichetRoot = query.from(Tichet.class);
        Join<Tichet, Bon> bonJoin = tichetRoot.join("bon");

        query.select(tichetRoot)
                .where(cb.equal(bonJoin.get("id"), bonId));

        TypedQuery<Tichet> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public Tichet findByName(String nume) {
        return null;
    }

    @Override
    public List<Tichet> findAll() {
        return tichetRepository.findAll();
    }

    @Override
    public List<Tichet> getTicheteWithCategorii() {
        return tichetRepository.getTicheteWithCategorie_tichet();
    }

    @Override
    public Tichet findTichetByBonId(Long id) {
        return tichetRepository.findByBonId(id);
    }

    @Override
    public Long getStocDisponibil(Long id) {
        return tichetRepository.getStocById(id);
    }


    @Override
    public List<Tichet> findByStocGreaterThan(Long stoc) {
        return null;
    }


//    @Override
//    @Transactional
//    public List<Tichet> create(String nrBon, List<Tichet> tichete) throws InsufficientResourcesException {
//        Bon bon = bonRepository.findByNrBon(nrBon);
//        Rezervare rezervare = rezervareRepository.findByBon(bon);
//        int nrPersoane = rezervare.getNrPersoane();
//        List<Categorie_Tichet> categoriiTichet = categorie_tichetRepository.findAll();
//
//        for (int i = 0; i < nrPersoane; i++) {
//            Tichet tichet = new Tichet();
//            Categorie_Tichet categorieTichet = categoriiTichet.get(i);
//
//            tichet.setBon(bon);
//            Set<Categorie_Tichet> categorii = new HashSet<>();
//            categorii.add(categorieTichet);
//            tichet.setCategorie_tichet(categorii);
//
//            tichetRepository.save(tichet);
//        }
//
//        return tichete;
//    }
private synchronized Long generateBiletCode() {
    lastBiletCode++;
    return lastBiletCode;
}

    private synchronized Long generateAbonamentCode() {
        lastAbonamentCode++; // Increment the counter for abonament codes
        return lastAbonamentCode;
    }

    @Override
    public List<Tichet> create(String nrBon, List<Tichet> tichete) throws InsufficientResourcesException {
        Bon bon = bonRepository.findByNrBon(nrBon);
        for (Tichet tichet: tichete) {
            tichet.setBon(bon);
            tichet.setStoc(tichet.getStoc()-tichete.size());
            if(TipTichet.BILET.equals(tichet.getTipTichet()))
                tichet.setNrBilet(generateBiletCode());
            else
                tichet.setCodAbonament(generateAbonamentCode());
            tichetRepository.save(tichet);
        }
        return tichete;
    }



//    @Override
//    public List<Tichet> create(String nrBon, List<Tichet> tichete) throws InsufficientResourcesException {
//        Bon bon = bonRepository.findByNrBon(nrBon);
//
//
//        for (Tichet tichet : tichete) {
//            if (tichet.getNrBilet() > tichet.getStoc()) {
//                throw new InsufficientResourcesException();
//            } else {
//                tichet.setStoc(tichet.getStoc() - tichet.getNrBilet());
//                tichet.setBon(bon);
//                tichetRepository.save(tichet);
//            }
//        }
//        return tichete;
//    }

    public void storeTicheteForToken(String token, List<Tichet> tichete) {
        ticheteTemporare.put(token, tichete);
    }

    public List<Tichet> getTicheteByToken(String token) {
        return ticheteTemporare.get(token);
    }

    @Override
    public void delete(List<Tichet> tichete) {
        tichetRepository.deleteAll(tichete);
    }
}


