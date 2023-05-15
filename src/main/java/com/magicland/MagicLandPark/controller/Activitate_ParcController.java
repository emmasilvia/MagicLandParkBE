package com.magicland.MagicLandPark.controller;

import com.magicland.MagicLandPark.controller.dto.activitate_parc.ActivitateRequestDto;
import com.magicland.MagicLandPark.controller.dto.activitate_parc.ActivitateResponseDto;
import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.service.Activitate_ParcService;
import com.magicland.MagicLandPark.service.PersoanaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Activitate_ParcController {

    private Activitate_ParcService activitate_parcService;
    private PersoanaService persoanaService;

    public Activitate_ParcController(Activitate_ParcService activitate_parcService, PersoanaService persoanaService){
        this.activitate_parcService = activitate_parcService;
        this.persoanaService = persoanaService;
    }

    @PostMapping(value = "/activitati")
    public ActivitateRequestDto create(@RequestBody ActivitateRequestDto activitateRequestDto){
        Activitate_Parc activitate_parc = activitate_parcService.create(mapActivitateRequestDtoToActivitate(activitateRequestDto));
        return mapActivitateToActivitateResponseDto(activitate_parc);
    }

    @GetMapping(value = "activitati/{id}")
    public Activitate_Parc findById(@PathVariable("id") Long id){
        return activitate_parcService.findById(id);
    }

    @GetMapping(value = "activitati/nume/{denumire}")
    public Activitate_Parc findByName(@PathVariable("denumire") String denumire){
        return activitate_parcService.findByName(denumire);
    }

    @DeleteMapping("/activitati/delete/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        try {
            activitate_parcService.delete(id);
            return HttpStatus.OK;
        } catch (ResursaNegasitaInDB resursaEroare) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping(value = "/activitati/{id}")
    public ActivitateRequestDto update(@PathVariable("id")Long id, @RequestBody ActivitateRequestDto activitateRequestDto){
        Activitate_Parc updateActivitate_parc = updateActivitateDtoToActivitate(activitate_parcService.findById(id), activitateRequestDto);
        return mapActivitateToActivitateResponseDto(activitate_parcService.create(updateActivitate_parc));
    }


    private ActivitateResponseDto mapActivitateToActivitateResponseDto(Activitate_Parc activitate_parc){
        ActivitateResponseDto activitateResponseDto = new ActivitateResponseDto();
        activitateResponseDto.setDenumire(activitate_parc.getDenumire());
        activitateResponseDto.setDescriere(activitate_parc.getDescriere());
        activitateResponseDto.setDurata(activitateResponseDto.getDurata());
        activitateResponseDto.setNivelDificultate(activitate_parc.getNivelDificultate());
        activitateResponseDto.setProgram(activitateResponseDto.getProgram());
        activitateResponseDto.setRezervari(activitate_parc.getRezervari());
        activitateResponseDto.setVarstaMinima(activitate_parc.getVarstaMinima());
        activitateResponseDto.setTipActivitate(activitate_parc.getTipActivitate());
        activitateResponseDto.setZone(activitate_parc.getZone());
        activitateResponseDto.setAnimale(activitate_parc.getAnimale());

        return activitateResponseDto;
    }

    private Activitate_Parc mapActivitateRequestDtoToActivitate(ActivitateRequestDto activitateRequestDto){
        Activitate_Parc activitate_parc = new Activitate_Parc();
        activitate_parc.setDenumire(activitateRequestDto.getDenumire());
        activitate_parc.setDescriere(activitateRequestDto.getDescriere());
        activitate_parc.setDurata(activitateRequestDto.getDurata());
        activitate_parc.setNivelDificultate(activitateRequestDto.getNivelDificultate());
        activitate_parc.setProgram(activitateRequestDto.getProgram());
        activitate_parc.setVarstaMinima(activitateRequestDto.getVarstaMinima());
        activitate_parc.setTipActivitate(activitateRequestDto.getTipActivitate());
        activitate_parc.setRezervari(activitateRequestDto.getRezervari());
        activitate_parc.setZone(activitateRequestDto.getZone());
        activitate_parc.setAnimale(activitateRequestDto.getAnimale());

        return activitate_parc;
    }

    private Activitate_Parc updateActivitateDtoToActivitate(Activitate_Parc activitate_parc, ActivitateRequestDto activitateRequestDto){

        activitate_parc.setDenumire(activitateRequestDto.getDenumire());
        activitate_parc.setDescriere(activitateRequestDto.getDescriere());
        activitate_parc.setDurata(activitate_parc.getDurata());
        activitate_parc.setNivelDificultate(activitateRequestDto.getNivelDificultate());
        activitate_parc.setProgram(activitateRequestDto.getProgram());
        activitate_parc.setVarstaMinima(activitateRequestDto.getVarstaMinima());
        activitate_parc.setTipActivitate(activitateRequestDto.getTipActivitate());
        activitate_parc.setZone(activitateRequestDto.getZone());

        return activitate_parc;
    }
}
