package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.data.TipoMedicamento;
import com.devinhouse.DEVinPharmacy.model.Endereco;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.service.FarmaciaRepositoryService;
import com.devinhouse.DEVinPharmacy.service.MedicamentoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inicializacao")
public class InicializacaoController {

    @Autowired
    FarmaciaRepositoryService farmaciaService;
    @Autowired
    MedicamentoRepositoryService medicamentoService;

    @PostMapping
    public ResponseEntity<HttpStatus> inicialize() {

        if (farmaciaService.GetAll().isEmpty()) {
            List<Farmacia> farmacias = this.farmaciaInicializacao();
            farmaciaService.SaveAll(farmacias);
        }

        if(medicamentoService.GetAll().isEmpty()) {
            List<Medicamento> medicamentos = this.medicamentoInicializacao();
            medicamentoService.SaveAll(medicamentos);
        }

        return ResponseEntity.ok().build();
    };


    private List<Farmacia> farmaciaInicializacao(){
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
                        "",
                        "Winterfell",
                        "Estocolmo",
                        "SC",
                        19.254356,
                        3.8987687
                )
        );
        return List.of(farmacia1,farmacia2);
    }

    private List<Medicamento> medicamentoInicializacao(){
        Medicamento medicamento1 = new Medicamento(
                1010,
                "Programapan",
                "Matrix",
                "2x ao dia",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend",
                111.00F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento2 = new Medicamento(
                7473,
                "Cafex",
                "Colombia Farm",
                "4x ao dia",
                "Descricao: Pellentesque non ultricies mauris, ut lobortis elit. Cras nec cursus nibh",
                51.50F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento3 = new Medicamento(
                2233,
                "Estomazol",
                "Acme",
                "1x ao dia",
                "Sed risus mauris, consectetur eget egestas vitae",
                22.50F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento4 = new Medicamento(
                8880,
                "Gelox",
                "Ice",
                "2x ao dia",
                "Quisque quam orci, vulputate sit amet",
                11.50F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento5 = new Medicamento(
                5656,
                "Aspirazol",
                "Acme",
                "3x ao dia",
                "Sed faucibus, libero iaculis pulvinar consequat, augue elit eleifend",
                10.50F,
                TipoMedicamento.CONTROLADO
        );

        Medicamento medicamento6 = new Medicamento(
                4040,
                "Propolizol",
                "Bee",
                "5x ao dia",
                "Nunc euismod ipsum purus, sit amet finibus libero ultricies in",
                10.50F,
                TipoMedicamento.CONTROLADO
        );

        return List.of(medicamento1,
                medicamento2,
                medicamento3,
                medicamento4,
                medicamento5,
                medicamento6
        );
    }

}
