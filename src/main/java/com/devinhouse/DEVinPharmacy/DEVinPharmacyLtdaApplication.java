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

	@Bean
	CommandLineRunner run(FarmaciaRepository farmaciaRepo){
		return args->{

			if(farmaciaRepo.findAll().isEmpty()) {
				Farmacia farmacia1 = new Farmacia();
				farmacia1.setCnpj(90561736000121L);
				farmacia1.setRazaoSocial("DevMed Ltda");
				farmacia1.setNomeFantasia("Farmácia DevMed");
				farmacia1.setEmail("devmed@farmacia.com");
				farmacia1.setTelefone("(44)4444-4444");
				farmacia1.setCelular("(44)9444-4441");

				Endereco endereco1 = new Endereco();
				endereco1.setCep(88888999L);
				endereco1.setLogradouro("Rua Porto Real");
				endereco1.setNumero(67);
				endereco1.setComplemento("");
				endereco1.setBairro("Westeros");
				endereco1.setCidade("Berlim");
				endereco1.setEstado("SC");
				endereco1.setLatitude(15.23456);
				endereco1.setLongitude(2.8678687);

				farmacia1.setEndereco(endereco1);
				farmaciaRepo.save(farmacia1);
			}

		};
	}
}
