package com.magicland.MagicLandPark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicland.MagicLandPark.controller.dto.tichet.CategorieTichetDto;
import com.magicland.MagicLandPark.model.*;
import com.magicland.MagicLandPark.service.Categorie_VarstaService;
import com.magicland.MagicLandPark.service.TichetService;
import com.magicland.MagicLandPark.service.TipAccesService;
import com.magicland.MagicLandPark.service.TipService;
import com.magicland.MagicLandPark.service.implementation.Categorie_TichetServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class MagicLandParkApplication implements CommandLineRunner {

	private TipService tipService;
	private Categorie_VarstaService categorie_varstaService;

	@Autowired
	private TichetService tichetService;

	@Autowired
	private Categorie_TichetServiceImplementation categorie_tichetServiceImplementation;

	@Autowired
	public MagicLandParkApplication(TipService tipService, Categorie_VarstaService categorie_varstaService,Categorie_TichetServiceImplementation categorie_tichetServiceImplementation){
		this.tipService = tipService;
		this.categorie_varstaService= categorie_varstaService;

		this.categorie_tichetServiceImplementation = categorie_tichetServiceImplementation;

	}

	public static void main(String[] args) {
		SpringApplication.run(MagicLandParkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<TipEnum> tipEnumList = Arrays.asList(TipEnum.values());
		for (TipEnum tipEnum: tipEnumList){
			Tip tip = new Tip(tipEnum.name());
			tipService.create(tip);
		}


		List<CategorieVarstaEnum> categorieVarstaEnumList = Arrays.asList(CategorieVarstaEnum.values());
		for (CategorieVarstaEnum categorieVarstaEnum: categorieVarstaEnumList){
			CategorieVarsta categorieVarsta = new CategorieVarsta(categorieVarstaEnum.name());
			categorie_varstaService.create(categorieVarsta);
		}
	}


//	@PostConstruct
//	public void init() {
//		// Apelați metoda pentru a genera tichetele pentru o anumită zi
//		LocalDateTime data = LocalDateTime.now(); // Data pentru care se generează tichetele
//		int cantitate = 1000; // Numărul de tichete de generat
//		tichetService.generareTichete(data, cantitate);
//	}
}
