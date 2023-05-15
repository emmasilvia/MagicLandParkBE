package com.magicland.MagicLandPark;

import com.magicland.MagicLandPark.model.Tip;
import com.magicland.MagicLandPark.model.TipEnum;
import com.magicland.MagicLandPark.service.TipService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MagicLandParkApplication implements CommandLineRunner {

	private TipService tipService;

	public MagicLandParkApplication(TipService tipService){
		this.tipService = tipService;
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
	}
}
