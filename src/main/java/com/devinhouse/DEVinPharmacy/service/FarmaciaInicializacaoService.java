package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Endereco;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaInicializacaoService {

    public List<Farmacia> inicializarDados(){
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
                        "Westeros",
                        "Berlim",
                        "SC",
                        "",
                        15.23456,
                        2.8678687
                )
        );

        Farmacia farmacia2 = new Farmacia(
                43178995000198L,
                "MedHouse Ltda",
                "Farmácia MedHouse",
                "medhouse@farmacia.com",
                "(55)5555-5555",
                "(45)95555-5555",
                new Endereco(
                        8877799L,
                        "Rua Madrid",
                        76,
                        "Winterfell",
                        "Estocolmo",
                        "SC",
                        "",
                        19.254356,
                        3.8987687
                )
        );
        return List.of(farmacia1,farmacia2);
    };
}
