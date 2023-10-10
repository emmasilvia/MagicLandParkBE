package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.Harta;
import com.magicland.MagicLandPark.model.TipActivitate;
import com.magicland.MagicLandPark.repository.Activitate_ParcRepository;
import com.magicland.MagicLandPark.repository.Activitate_Parc_Specification;
import com.magicland.MagicLandPark.repository.HartaRepository;
import com.magicland.MagicLandPark.service.Activitate_ParcService;
import com.magicland.MagicLandPark.service.HartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActivitateServiceImplementation implements Activitate_ParcService {

    private Activitate_ParcRepository activitate_parcRepository;

    @Autowired
    private HartaRepository hartaRepository;

    @Autowired
    public ActivitateServiceImplementation(Activitate_ParcRepository activitati_parcRepository, HartaRepository hartaRepository){
        this.activitate_parcRepository = activitati_parcRepository;
        this.hartaRepository = hartaRepository;
    }


    @Override
    public Activitate_Parc create(Activitate_Parc activitate_parc) {
       return activitate_parcRepository.save(activitate_parc);
    }

    @Override
    public Activitate_Parc findById(Long id) {
        Optional<Activitate_Parc> optionalActivitate_parc = activitate_parcRepository.findById(id);
        if(optionalActivitate_parc.isPresent()){
            return optionalActivitate_parc.get();
        }else{
            throw new ResursaNegasitaInDB(String.format("Activitatea cu id-ul %d nu exista",id));
        }

    }

    @Override
    public Activitate_Parc findByName(String denumire) {
        Optional<Activitate_Parc> optionalActivitate_parc = activitate_parcRepository.findByDenumire(denumire);
        if(optionalActivitate_parc.isPresent()){
            return optionalActivitate_parc.get();
        }else{
            throw new ResursaNegasitaInDB(String.format("Activitatea cu numele %s nu exista",denumire));
        }
    }

    @Override
    public Activitate_Parc update(Activitate_Parc activitate_parc) {
        Activitate_Parc activitateExistenta = activitate_parcRepository.findById(activitate_parc.getId()).orElse(null);
        activitateExistenta.setDenumire(activitate_parc.getDenumire());
        activitateExistenta.setDescriere(activitate_parc.getDescriere());
        activitateExistenta.setDurata(activitate_parc.getDurata());
        activitateExistenta.setNivelDificultate(activitate_parc.getNivelDificultate());
        activitateExistenta.setProgram(activitate_parc.getProgram());
        activitateExistenta.setRezervari(activitate_parc.getRezervari());
        activitateExistenta.setTipActivitate(activitate_parc.getTipActivitate());
        activitateExistenta.setVarstaMinima(activitate_parc.getVarstaMinima());
        Harta harta = hartaRepository.findByDenumire(activitate_parc.getZonaHarta().toString());
        Set<Harta> zoneHarta = new HashSet<>();
        zoneHarta.add(harta);
        activitate_parc.setZonaHarta(zoneHarta);
        activitateExistenta.setAnimale(activitate_parc.getAnimale());

        return activitate_parcRepository.save(activitateExistenta);
    }

//    @Override
//    public List<Activitate_Parc> findAll() {
//        return activitate_parcRepository.findAll();
//    }

    @Override
    public Page<Activitate_Parc> searchActivities(Map<String, String> params) {
        Specification<Activitate_Parc> specification = new Activitate_Parc_Specification();

        if(params.get("page") == null || params.get("pageSize") == null) {
            throw new RuntimeException("page and pageSize must be valued");
        }
        Integer page = Integer.valueOf(params.get("page"));
        Integer pageSize = Integer.valueOf(params.get("pageSize"));

        for (String parameterName : params.keySet()){
            if(parameterName.equals("id")) {
                List<Long> activitiesIds = getActivitatiId(Long.valueOf(params.get(parameterName)));
                specification = specification.and(
                        Activitate_Parc_Specification.getSpecificationByParameterWithMultipleValues(parameterName, activitiesIds));
            }else {
                specification = specification
                        .and(Activitate_Parc_Specification.getSpecificationByParameter(parameterName,params.get(parameterName)));
            }
        }
        return activitate_parcRepository.findAll(specification, PageRequest.of(page,pageSize));
    }

    private List<Long> getActivitatiId(Long activitatiId) {

        Activitate_Parc activitate_parc = activitate_parcRepository.findById(activitatiId).get();
        List<Long> activitatiIds = new ArrayList<>();
        activitatiIds.add(activitate_parc.getId());

        return activitatiIds;
    }


    @Override
    public void delete(Long id) {
        activitate_parcRepository.deleteById(id);
    }

    @Override
    public List<Activitate_Parc> findByZonaHarta(Harta harta) {
        return activitate_parcRepository.findByZonaHarta(harta);
    }

    @Override
    public List<Activitate_Parc> findAll() {
        return activitate_parcRepository.findAll();
    }
}
