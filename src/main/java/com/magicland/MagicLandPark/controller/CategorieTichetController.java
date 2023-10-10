package com.magicland.MagicLandPark.controller;

import com.magicland.MagicLandPark.controller.dto.rezervare.RezervareRequestDto;
import com.magicland.MagicLandPark.controller.dto.tichet.CategorieTichetDto;
import com.magicland.MagicLandPark.model.CategorieVarsta;
import com.magicland.MagicLandPark.model.Categorie_Tichet;
import com.magicland.MagicLandPark.model.Rezervare;
import com.magicland.MagicLandPark.model.TipAcces;
import com.magicland.MagicLandPark.service.Categorie_TichetService;
import com.magicland.MagicLandPark.service.Categorie_VarstaService;
import com.magicland.MagicLandPark.service.TipAccesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InsufficientResourcesException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CategorieTichetController {

    private Categorie_TichetService categorie_tichetService;

    private TipAccesService tipAccesService;

    private Categorie_VarstaService categorie_varstaService;

    public CategorieTichetController(Categorie_TichetService categorie_tichetService,TipAccesService tipAccesService,Categorie_VarstaService categorie_varstaService){
        this.categorie_tichetService = categorie_tichetService;
        this.tipAccesService = tipAccesService;
        this.categorie_varstaService = categorie_varstaService;
    }

    @PostMapping(value = "/adauga-CategoriiTichete")
    public CategorieTichetDto create(@RequestBody CategorieTichetDto categorieTichetDto) throws InsufficientResourcesException {
        Categorie_Tichet categorie_tichet = categorie_tichetService.create(mapCategorieTichetDtoToCategorieTichet(categorieTichetDto));
        return mapCategorieTichetToCategorieTichetDto(categorie_tichet);
    }

    @GetMapping(value = "/categorii-tichete")
    public List<Categorie_Tichet> getAllCategories() {
        return categorie_tichetService.findAll();
    }

    private CategorieTichetDto mapCategorieTichetToCategorieTichetDto(Categorie_Tichet categorie_tichet) {
        CategorieTichetDto categorieTichetDto = new CategorieTichetDto();


//        Set<CategorieVarsta> categoriiDeVarsta = categorie_tichet.getCategorie_varsta();
//        for (CategorieVarsta categorieVarsta: categoriiDeVarsta) {
//            categorieTichetDto.setCategorieVarsta(categorieVarsta.getNume());
//        }

        categorieTichetDto.setCategorieVarsta(categorie_tichet.getCategorieVarsta());
        categorieTichetDto.setTarif(categorie_tichet.getTarif());

        return categorieTichetDto;
    }

    private Categorie_Tichet mapCategorieTichetDtoToCategorieTichet(CategorieTichetDto categorieTichetDto) {
        Categorie_Tichet categorieTichet = new Categorie_Tichet();


//        TipAcces tipAcces = tipAccesService.findByName(categorieTichetDto.getTipAcces());
//        Set<TipAcces> tipuriDeAcces = new HashSet<>();
//        tipuriDeAcces.add(tipAcces);
//        categorieTichet.setTipAcces(tipuriDeAcces);
//
//        CategorieVarsta categorieVarsta = categorie_varstaService.findByName(categorieTichetDto.getCategorieVarsta());
//        Set<CategorieVarsta> categoriiDeVarsta = new HashSet<>();
//        categoriiDeVarsta.add(categorieVarsta);
//        categorieTichet.setCategorie_varsta(categoriiDeVarsta);

        categorieTichet.setCategorieVarsta(categorieTichetDto.getCategorieVarsta());
        categorieTichet.setTarif(categorieTichetDto.getTarif());

        return categorieTichet;
    }
}
