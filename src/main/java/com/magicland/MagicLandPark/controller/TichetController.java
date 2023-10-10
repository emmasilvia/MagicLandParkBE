package com.magicland.MagicLandPark.controller;

import com.magicland.MagicLandPark.controller.dto.bon.BonResponseDto;
import com.magicland.MagicLandPark.controller.dto.tichet.CategorieTichetDto;
import com.magicland.MagicLandPark.controller.dto.tichet.TichetRequestDto;
import com.magicland.MagicLandPark.controller.dto.tichet.TichetResponseDto;
import com.magicland.MagicLandPark.model.*;
import com.magicland.MagicLandPark.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import java.util.*;

@RestController
public class TichetController {

    @Autowired
    private TichetService tichetService;
    private BonService bonService;


    private TipAccesService tipAccesService;

    private Categorie_VarstaService categorie_varstaService;

    @Autowired
    private Categorie_TichetService categorie_tichetService;

    @Autowired
    private RezervareService rezervareService;

    @Autowired
    public TichetController(TichetService tichetService, BonService bonService, Categorie_TichetService categorie_tichetService,RezervareService rezervareService){
        this.tichetService = tichetService;
        this.bonService = bonService;
        this.categorie_tichetService = categorie_tichetService;
        this.rezervareService = rezervareService;
    }

//    @PostMapping("/tichete")
//    public TichetRequestDto create(@RequestParam ("nrBon")String nrBon,@RequestBody TichetRequestDto tichetRequestDto) throws InsufficientResourcesException {
//
//        Tichet tichet = tichetService.create(mapTichetRequestDtoToTichet(tichetRequestDto));
//        return mapTichetToTichetResponseDto(tichet);
//    }

    @PostMapping("/tichete")
    public List<TichetResponseDto> create(@RequestParam(value = "nrBon") String nrBon, @RequestBody List<TichetRequestDto> ticheteDtoList) throws InsufficientResourcesException {
        List<Tichet> tichetList = tichetService.create(nrBon, mapTichetRequestDtoListToTichetList(ticheteDtoList));
        return mapTichetListToTichetResponseDtoList(tichetList);

    }


    @GetMapping(value = "tichete/all")
    public List<TichetResponseDto> findAll(){
        List<Tichet> tichetList = tichetService.findAll();
        return mapTichetListToTichetResponseDtoList(tichetList);
    }
    @GetMapping("/withCategorii")
    public ResponseEntity<List<Tichet>> getTicheteWithCategorii() {
        List<Tichet> tichete = tichetService.getTicheteWithCategorii();
        return ResponseEntity.ok(tichete);
    }

    @GetMapping(value = "tichete/bonuri/{id}")
    public List<TichetResponseDto> findTicheteByBonId(@PathVariable("id") Long bonId){
        List<Tichet> tichetList = tichetService.findTicheteByBon(bonId);
        return mapTichetListToTichetResponseDtoList(tichetList);
    }

    @GetMapping("/tichete")
    public BonResponseDto getBonId(@RequestParam("nr_bon") String nrBon){
        // Call the service to get the Bon object by nrBon
        Bon bon = bonService.getBonIdByNrBon(nrBon);

        // Convert the Bon object to BonResponseDto
        BonResponseDto bonResponseDto = mapBonToBonDto(bon);

        // Return the BonResponseDto as the response
        return bonResponseDto;
    }

    @GetMapping(value = "/tipuriTichet")
    public List<TipTichet> getTipuriTichet() {
        return Arrays.asList(TipTichet.values());
    }


    private List<TichetResponseDto> mapTichetListToTichetResponseDtoList(List<Tichet> tichetList) {
        List<TichetResponseDto> tichetDtoList = new ArrayList<>();
        for (Tichet tichet : tichetList) {
            TichetResponseDto tichetResponseDto = mapTichetToTichetResponseDto(tichet);
            tichetDtoList.add(tichetResponseDto);
        }
        return tichetDtoList;
    }


    private List<Tichet> mapTichetRequestDtoListToTichetList(List<TichetRequestDto> tichetRequestDtoList) {
        List<Tichet> tichetList = new ArrayList<>();
        for (TichetRequestDto tichetRequestDto : tichetRequestDtoList) {
            Tichet tichet = mapTichetRequestDtoToTichet(tichetRequestDto);
            tichetList.add(tichet);
        }
        return tichetList;
    }

    public TichetResponseDto mapTichetToTichetResponseDto(Tichet tichet) {
        TichetResponseDto tichetResponseDto = new TichetResponseDto();
        tichetResponseDto.setId(tichet.getId());
        tichetResponseDto.setNrBilet(tichet.getNrBilet());
        tichetResponseDto.setCodAbonament(tichet.getCodAbonament());
        tichetResponseDto.setDurataAbonament(tichet.getDurataAbonament());
        tichetResponseDto.setCategorie_tichet(tichet.getCategorieTichet().getCategorieVarsta());
//        Set<Categorie_Tichet> categorie_tichete = tichet.getCategorieVarsta();
//        for (Categorie_Tichet categorieTichet : categorie_tichete){
//            tichetResponseDto.setCategorie_tichet(categorieTichet.getCategorieVarsta());
//        }
        tichetResponseDto.setValabilitate(tichet.getValabilitate());
        tichetResponseDto.setStoc(tichet.getStoc());
        tichetResponseDto.setBon(tichet.getBon());
        tichetResponseDto.setTipTichet(tichet.getTipTichet());
        return tichetResponseDto;
    }




    private Tichet mapTichetRequestDtoToTichet(TichetRequestDto tichetRequestDto) {
        Tichet tichet = new Tichet();

        tichet.setDurataAbonament(tichetRequestDto.getDurataAbonament());

//        Categorie_Tichet categorie_tichet = categorie_tichetService.findByName(tichetRequestDto.getCategorie_tichet());
//        Set<Categorie_Tichet> categorie_tichete = new HashSet<>();
//        categorie_tichete.add(categorie_tichet);
//        tichet.setCategorie_tichet(categorie_tichete);

        Categorie_Tichet categorie_tichet = categorie_tichetService.findByName(tichetRequestDto.getCategorie_tichet());
        tichet.setCategorieTichet(categorie_tichet);

        tichet.setTipTichet(tichetRequestDto.getTipTichet());
        return tichet;
    }


    private BonResponseDto mapBonToBonDto(Bon bon) {
        BonResponseDto bonResponseDto= new BonResponseDto();
        bonResponseDto.setId(bon.getId());
        bonResponseDto.setNrBon(bon.getNrBon());
        bonResponseDto.setDataBon(bon.getDataBon());

        return bonResponseDto;
    }

    private CategorieTichetDto mapCategorieToCategorieDto(Categorie_Tichet categorie_tichet){
        CategorieTichetDto categorieTichetDto = new CategorieTichetDto();
        categorieTichetDto.setId(categorie_tichet.getId());
        categorieTichetDto.setCategorieVarsta(categorie_tichet.getCategorieVarsta());
        categorieTichetDto.setTarif(categorieTichetDto.getTarif());
        return categorieTichetDto;
    }

    private Categorie_Tichet mapCategorieDtoToCategorie(CategorieTichetDto categorieTichetDto){
        Categorie_Tichet categorie_tichet = new Categorie_Tichet();
        categorie_tichet.setId(categorieTichetDto.getId());
        categorie_tichet.setCategorieVarsta(categorieTichetDto.getCategorieVarsta());
        categorie_tichet.setTarif(categorieTichetDto.getTarif());
        return categorie_tichet;
    }



//    private Set<CategorieTichetDto> mapCategorieTichetSetToCategorieTichetDtoSet(Set<Categorie_Tichet> categorieTichetSet) {
//        Set<CategorieTichetDto> categorieTichetDtoSet = new HashSet<>();
//        for (Categorie_Tichet categorieTichet : categorieTichetSet) {
//            CategorieTichetDto categorieTichetDto = mapCategorieTichetToCategorieTichetDto(categorieTichet);
//            categorieTichetDtoSet.add(categorieTichetDto);
//        }
//        return categorieTichetDtoSet;
//    }

//    private CategorieTichetDto mapCategorieTichetToCategorieTichetDto(Categorie_Tichet categorieTichet) {
//        CategorieTichetDto categorieTichetDto = new CategorieTichetDto();
//        categorieTichetDto.setDenumire(categorieTichet.getDenumire());
//        Set<TipAcces> tipuri_acces= categorieTichet.getTipAcces();
//        for (TipAcces tipAcces : tipuri_acces){
//            categorieTichetDto.setTipAcces(tipAcces.getNume());
//        }
//
//
//
//        Set<CategorieVarsta> categorii_varste= categorieTichet.getCategorieVarsta();
//        for (CategorieVarsta categorieVarsta : categorii_varste){
//            categorieTichetDto.setCategorieVarsta(categorieVarsta.getNume());
//        }
//
//        categorieTichetDto.setTarif(categorieTichet.getTarif());
//
//        return categorieTichetDto;
//    }





//    private Set<Categorie_Tichet> mapCategorieTichetDtoSetToCategorieTichetSet(Set<CategorieTichetDto> categorieTichetDtoSet) {
//        Set<Categorie_Tichet> categorieTichetSet = new HashSet<>();
//        if (categorieTichetDtoSet != null) { // Check for null
//            for (CategorieTichetDto categorieTichetDto : categorieTichetDtoSet) {
//                Categorie_Tichet categorieTichet = mapCategorieTichetDtoToCategorieTichet(categorieTichetDto);
//                categorieTichetSet.add(categorieTichet);
//            }
//        }
//        return categorieTichetSet;
//    }
//
//
//    private Categorie_Tichet mapCategorieTichetDtoToCategorieTichet(CategorieTichetDto categorieTichetDto) {
//        Categorie_Tichet categorieTichet = new Categorie_Tichet();
//        categorieTichet.setDenumire(categorieTichetDto.getDenumire());
//
//        TipAcces tipAcces = tipAccesService.findByName(categorieTichetDto.getTipAcces());
//        Set<TipAcces> tipuri_acces = new HashSet<>();
//        tipuri_acces.add(tipAcces);
//        categorieTichet.setTipAcces(tipuri_acces);
//
//
//        CategorieVarsta categorieVarsta = categorie_varstaService.findByName(categorieTichetDto.getCategorieVarsta());
//        Set<CategorieVarsta> categorii_varste = new HashSet<>();
//        categorii_varste.add(categorieVarsta);
//        categorieTichet.setCategorieVarsta(categorii_varste);
//
//        categorieTichet.setTarif(categorieTichetDto.getTarif());
//
//        return categorieTichet;
//    }



}