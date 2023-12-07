package com.devinhouse.DEVinPharmacy;

import com.devinhouse.DEVinPharmacy.model.Endereco;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.repository.FarmaciaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DEVinPharmacyLtdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DEVinPharmacyLtdaApplication.class, args);
	}

	//TODO: Insert the other data.
	@Bean
	CommandLineRunner run(FarmaciaRepository farmaciaRepo){
		return args->{

			if(farmaciaRepo.findAll().isEmpty()) {
				Farmacia farmacia1 = new Farmacia(
						90561736000121L,
						"DevMed Ltda",
						"Farmácia DevMed",
						"devmed@farmacia.com",
						"(44)4444-4444",
						"(44)9444-4441",
						new Endereco(
								88888999L,
								"Rua Porto Real",
								67,
								"",
								"Westeros",
								"Berlim",
								"SC",
								15.23456,
								2.8678687)
				);
				farmaciaRepo.save(farmacia1);
			}

		};
	}
}
