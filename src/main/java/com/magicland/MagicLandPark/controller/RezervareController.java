package com.magicland.MagicLandPark.controller;

import com.magicland.MagicLandPark.controller.dto.bon.BonRequestDto;
import com.magicland.MagicLandPark.controller.dto.rezervare.*;
import com.magicland.MagicLandPark.controller.dto.tichet.CategorieTichetDto;
import com.magicland.MagicLandPark.controller.dto.tichet.ReservationDetailsDto;
import com.magicland.MagicLandPark.controller.dto.tichet.TichetDetailsDto;
import com.magicland.MagicLandPark.controller.dto.tichet.TichetResponseDto;
import com.magicland.MagicLandPark.exception.ResursaNegasitaInDB;
import com.magicland.MagicLandPark.model.*;
import com.magicland.MagicLandPark.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class RezervareController {

    private RezervareService rezervareService;

    private PersoanaService persoanaService;

    private TichetService tichetService;

    private BonService bonService;

    private Categorie_TichetService categorie_TichetService;

    private TipAccesService tipAccesService;

    private Activitate_ParcService activitate_parcService;

    @Autowired
    public RezervareController(RezervareService rezervareService, PersoanaService persoanaService, TichetService tichetService,
                               Categorie_TichetService categorie_tichetService,TipAccesService tipAccesService,
                               Activitate_ParcService activitate_parcService, BonService bonService) {

        this.rezervareService = rezervareService;
        this.persoanaService = persoanaService;
        this.tichetService = tichetService;
        this.categorie_TichetService = categorie_tichetService;
        this.tipAccesService = tipAccesService;
        this.activitate_parcService = activitate_parcService;
        this.bonService = bonService;
    }

    @PostMapping(value = "/rezervare")
    public RezervareRequestDto create(@RequestBody RezervareRequestDto rezervareRequestDto) throws InsufficientResourcesException {
        Rezervare rezervare = rezervareService.createReservation(mapRezervareRequestDtoToRezervare(rezervareRequestDto));
        return mapRezervareToRezervareResponseDto(rezervare);
    }


    @GetMapping(value = "/rezervare/{id}")
    public Rezervare findById(@PathVariable("id") Long id){
        return rezervareService.findById(id);
    }

    @GetMapping(value = "/rezervare/nrBon/{nrBon}")
    public Rezervare findByBon(@PathVariable("nrBon") String nrBon) {
        return rezervareService.findByNrBon(nrBon);
    }



    @GetMapping(value = "/persoane/revervari/{email}")
    public List<RezervareResponseDto> findAllReservationsByPersoanaId(@PathVariable("email") String email){
        List<Rezervare> rezervari = rezervareService.findAllReservationsByEmail(email);
        return mapRezervareListToRezervareResponseDtoList(rezervari);
    }

    @GetMapping(value = "rezervari")
    public List<RezervareResponseDto> findAllReservations(){
        List<Rezervare> rezervari = rezervareService.findAll();
        return mapRezervareListToRezervareResponseDtoList(rezervari);
    }


    @DeleteMapping("/rezervari/delete/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        try {
            rezervareService.deleteRezervare(id);
            return HttpStatus.OK;
        }catch (ResursaNegasitaInDB resursaNegasitaInDB){
            return HttpStatus.NOT_FOUND;
        }
    }


    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        Optional<Rezervare> reservationOptional = rezervareService.findByIdOptional(id);

        if (reservationOptional.isPresent()) {
            Rezervare reservation = reservationOptional.get();

            // Delete associated tichets
            Bon bon = reservation.getBon();
            List<Tichet> tichete = tichetService.findTicheteByBon(bon.getId());
            tichetService.delete(tichete); // Implement a method to delete multiple tichets

            // Delete the bon
            bonService.deleteBon(bon.getId()); // Implement a method to delete the bon

            // Delete the reservation
            rezervareService.deleteRezervare(id); // Implement a method to delete the reservation

            return ResponseEntity.ok("Reservation, Bon, and associated Tichets deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




//    @GetMapping("/reservation/{id}/details")
//    public ResponseEntity<Map<String, Object>> getReservationDetails(@PathVariable Long id) {
//        Optional<Rezervare> reservationOptional = rezervareService.findByIdOptional(id);
//
//        if (reservationOptional.isPresent()) {
//                Rezervare reservation = reservationOptional.get();
//                Bon bon = reservation.getBon();
//                List<Tichet> tichete = tichetService.findTicheteByBon(bon.getId());
//
//                Map<String, Object> details = new HashMap<>();
//                details.put("bon", bon);
//                details.put("tichete", tichete);
//
//
//                return ResponseEntity.ok(details);
//        } else {
//                return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/reservation/{id}/details")
    public ResponseEntity<Map<String, Object>> getReservationDetails(@PathVariable Long id) {
        Optional<Rezervare> reservationOptional = rezervareService.findByIdOptional(id);

        if (reservationOptional.isPresent()) {
            Rezervare reservation = reservationOptional.get();
            Bon bon = reservation.getBon();
            List<Tichet> tichete = tichetService.findTicheteByBon(bon.getId());

            List<TichetDetailsDto> tichetDetailsList = new ArrayList<>();

            for (Tichet tichet : tichete) {

                Long categorieTichetId = tichet.getCategorieTichet().getId();

                Categorie_Tichet categorieTichet = categorie_TichetService.findById(categorieTichetId);

                TichetDetailsDto tichetDetails = new TichetDetailsDto();
                tichetDetails.setTichet(tichet);
                tichetDetails.setCategorie_tichet(categorieTichet);

                tichetDetailsList.add(tichetDetails);
            }

            Map<String, Object> details = new HashMap<>();
            details.put("bon", bon);
            details.put("tichete", tichetDetailsList);

            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/reservation/{id}/details")
//    public ResponseEntity<Map<String, Object>> getReservationDetails(@PathVariable Long id) {
//        Optional<Rezervare> reservationOptional = rezervareService.findByIdOptional(id);
//
//        if (reservationOptional.isPresent()) {
//            Rezervare reservation = reservationOptional.get();
//            Bon bon = reservation.getBon();
//            List<Tichet> tichete = tichetService.findAll();
//
//            // Creați o nouă listă pentru a stoca tichetele dorite
//            List<Tichet> ticheteDorite = new ArrayList<>();
//
//            for (Tichet tichet : tichete) {
//                if (tichet.getBon() != null && tichet.getBon().equals(bon)) {
//                    // Tichetul aparține bonului dorit, adăugați-l la lista de tichete dorite
//                    ticheteDorite.add(tichet);
//                }
//            }
//
//            Map<String, Object> details = new HashMap<>();
//            details.put("bon", bon);
//            details.put("tichete", ticheteDorite);
//
//            return ResponseEntity.ok(details);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    public Rezervare mapRezervareRequestDtoToRezervare(RezervareRequestDto rezervareRequestDto){
        Rezervare rezervare = new Rezervare();
        rezervare.setDataRezervare(rezervareRequestDto.getDataRezervare());
        rezervare.setDataVizita(rezervareRequestDto.getDataVizita());
        rezervare.setNrPersoane(rezervareRequestDto.getNrPersoane());
        Persoana persoana = persoanaService.findByEmail(rezervareRequestDto.getPersoana());
        rezervare.setPersoana(persoana);
        Activitate_Parc activitate_parc = activitate_parcService.findById(rezervareRequestDto.getActivitateId());
        rezervare.setActivitate(activitate_parc);
        rezervare.setBon(mapBonRequestDtoToBon(rezervareRequestDto.getBon()));
        return rezervare;
    }

    public RezervareResponseDto mapRezervareToRezervareResponseDto(Rezervare rezervare) {
        RezervareResponseDto rezervareResponseDto = new RezervareResponseDto();
        rezervareResponseDto.setId(rezervare.getId());
        rezervareResponseDto.setDataRezervare(rezervare.getDataRezervare());
        rezervareResponseDto.setDataVizita(rezervare.getDataVizita());
        rezervareResponseDto.setPersoana(rezervare.getPersoana().getEmail());
        rezervareResponseDto.setNrPersoane(rezervare.getNrPersoane());
        rezervareResponseDto.setBon(mapBonToBonRequestDto(rezervare.getBon()));
        rezervareResponseDto.setActivitateId(rezervare.getActivitate().getId());
        return rezervareResponseDto;
    }

    private List<RezervareResponseDto> mapRezervareListToRezervareResponseDtoList(List<Rezervare> rezervareList) {
        List<RezervareResponseDto> rezervareResponseList = new ArrayList<>();
        for (Rezervare rezervare : rezervareList) {
            RezervareResponseDto rezervareResponseDto = mapRezervareToRezervareResponseDto(rezervare);
            rezervareResponseList.add(rezervareResponseDto);
        }
        return rezervareResponseList;
    }






    public Bon mapBonRequestDtoToBon(BonRequestDto bonRequestDto){
        Bon bon = new Bon();
        bon.setNrBon(bonRequestDto.getNrBon());
        bon.setDataBon(bonRequestDto.getDataBon());
        return bon;
    }

    private BonRequestDto mapBonToBonRequestDto(Bon bon) {
        BonRequestDto bonRequestDto= new BonRequestDto();
        bonRequestDto.setNrBon(bon.getNrBon());
        bonRequestDto.setDataBon(bon.getDataBon());

        return bonRequestDto;
    }

    //   "valabilitate": "2023-06-10T09:00:00",
    //   "rezervare": null,
}
