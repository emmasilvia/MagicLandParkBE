package com.magicland.MagicLandPark.service.implementation;

import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.repository.Activitate_ParcRepository;
import com.magicland.MagicLandPark.repository.Activitate_Parc_Specification;
import com.magicland.MagicLandPark.service.Activitate_ParcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ActivitateServiceImplementation implements Activitate_ParcService {

    private Activitate_ParcRepository activitati_parcRepository;

    @Autowired
    public ActivitateServiceImplementation(Activitate_ParcRepository activitati_parcRepository){
        this.activitati_parcRepository = activitati_parcRepository;
    }


    @Override
    public Activitate_Parc create(Activitate_Parc activitate_parc) {
       return activitati_parcRepository.save(activitate_parc);
    }

    @Override
    public Activitate_Parc findById(Long id) {
        Optional<Activitate_Parc> optionalActivitate_parc = activitati_parcRepository.findById(id);
        if(optionalActivitate_parc.isPresent()){
            return optionalActivitate_parc.get();
        }else{
            throw new ResursaNegasitaInDB(String.format("Activitatea cu id-ul %d nu exista",id));
        }

    }

    @Override
    public Activitate_Parc findByName(String denumire) {
        Optional<Activitate_Parc> optionalActivitate_parc = activitati_parcRepository.findByDenumire(denumire);
        if(optionalActivitate_parc.isPresent()){
            return optionalActivitate_parc.get();
        }else{
            throw new ResursaNegasitaInDB(String.format("Activitatea cu numele %s nu exista",denumire));
        }
    }

    @Override
    public Activitate_Parc update(Activitate_Parc activitate_parc) {
        Activitate_Parc activitateExistenta = activitati_parcRepository.findById(activitate_parc.getId()).orElse(null);
        activitateExistenta.setDenumire(activitate_parc.getDenumire());
        activitateExistenta.setDescriere(activitate_parc.getDescriere());
        activitateExistenta.setDurata(activitate_parc.getDurata());
        activitateExistenta.setNivelDificultate(activitate_parc.getNivelDificultate());
        activitateExistenta.setProgram(activitate_parc.getProgram());
        activitateExistenta.setRezervari(activitate_parc.getRezervari());
        activitateExistenta.setTipActivitate(activitate_parc.getTipActivitate());
        activitateExistenta.setVarstaMinima(activitate_parc.getVarstaMinima());
        activitateExistenta.setZone(activitate_parc.getZone());
        activitateExistenta.setAnimale(activitate_parc.getAnimale());

        return activitati_parcRepository.save(activitateExistenta);
    }

    @Override
    public Page<Activitate_Parc> searchActivities(Map<String, String> params) {
        Specification<Activitate_Parc> specification = new Activitate_Parc_Specification();

        if(params.get("pagina") == null || params.get("dimPagina") == null) {
            throw new RuntimeException("pagina si dimPagina trebuie sa aiba valori");
        }
        Integer pagina = Integer.valueOf(params.get("pagina"));
        Integer dimPagina = Integer.valueOf(params.get("dimPagina"));

        for(String parameterName :params.keySet()){
            if(parameterName.equals("categorieId")){
              //todo
            }
        }

        return activitati_parcRepository.findAll(specification, PageRequest.of(pagina,dimPagina));
    }

    @Override
    public void delete(Long id) {
        activitati_parcRepository.deleteById(id);
    }

}
