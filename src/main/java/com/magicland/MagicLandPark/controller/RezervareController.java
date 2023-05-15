package com.magicland.MagicLandPark.controller;

import com.magicland.MagicLandPark.controller.dto.bon.BonRequestDto;
import com.magicland.MagicLandPark.controller.dto.rezervare.RezervareRequestDto;
import com.magicland.MagicLandPark.controller.dto.rezervare.RezervareResponseDto;
import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.Bon;
import com.magicland.MagicLandPark.model.Rezervare;
import com.magicland.MagicLandPark.service.PersoanaService;
import com.magicland.MagicLandPark.service.RezervareService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InsufficientResourcesException;

@RestController
public class RezervareController {

    private RezervareService rezervareService;

    private PersoanaService persoanaService;

    public RezervareController(RezervareService rezervareService, PersoanaService persoanaService) {

        this.rezervareService = rezervareService;
        this.persoanaService = persoanaService;
    }

    @PostMapping(value = "/rezervari")
    public RezervareRequestDto create(@RequestBody RezervareRequestDto rezervareRequestDto) throws InsufficientResourcesException {
        Rezervare rezervare = rezervareService.createReservation(mapRezervareRequestDtoToRezervare(rezervareRequestDto));
        return mapRezervareToRezervareResponseDto(rezervare);
    }

//    @PostMapping("/rezervari")
//    public ResponseEntity<Object> createReservation(@RequestBody RezervareDto rezervareDto, @RequestBody BonDto bonDto) {
//        try {
//            Rezervare rezervare = mapDtoToRezervare(rezervareDto);
//            Bon bon = mapDtoToBon(bonDto);
//            Rezervare createdRezervare = rezervareService.createReservation(rezervare, bon);
//            RezervareDto createdRezervareDto = mapRezervareToDto(createdRezervare);
//            BonDto createdBonDto = mapBonToDto(createdRezervare.getBon());
//            return ResponseEntity.ok().body(Arrays.asList(createdRezervareDto, createdBonDto));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    public Rezervare mapRezervareRequestDtoToRezervare(RezervareRequestDto rezervareRequestDto){
        Rezervare rezervare = new Rezervare();
        rezervare.setDataRezervare(rezervareRequestDto.getDataRezervare());
        rezervare.setDataVizita(rezervareRequestDto.getDataVizita());
        rezervare.setNrPersoane(rezervareRequestDto.getNrPersoane());
        rezervare.setPersoana(rezervareRequestDto.getEmail_persoana());
        rezervare.setActivitate_parc(rezervareRequestDto.getDenumire_activitate());
        rezervare.setCategorie_varsta(rezervareRequestDto.getCategorie_varsta());

        return rezervare;
    }

    public RezervareResponseDto mapRezervareToRezervareResponseDto(Rezervare rezervare){
        RezervareResponseDto rezervareResponseDto = new RezervareResponseDto();
        rezervareResponseDto.setDataRezervare(rezervare.getDataRezervare());
        rezervareResponseDto.setDataVizita(rezervare.getDataVizita());
        rezervareResponseDto.setNrPersoane(rezervare.getNrPersoane());
        rezervareResponseDto.setDenumire_activitate(rezervare.getActivitate_parc());
        rezervareResponseDto.setEmail_persoana(rezervare.getPersoana());
        rezervareResponseDto.setCategorie_varsta(rezervare.getCategorie_varsta());

        return rezervareResponseDto;
    }

    public Bon mapBonRequestDtoToBon(BonRequestDto bonRequestDto){
        Bon bon = new Bon();
        bon.setNrBon(bonRequestDto.getNrBon());
        bon.setDataBon(bonRequestDto.getDataBon());
        return bon;
    }
}
