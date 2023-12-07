package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.service.FarmaciaInicializacaoService;
import com.devinhouse.DEVinPharmacy.service.FarmaciaRepositoryService;
import com.devinhouse.DEVinPharmacy.service.MedicamentoInicializacaoService;
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
    FarmaciaInicializacaoService farmaciaInicializacaoService;
    @Autowired
    MedicamentoRepositoryService medicamentoService;
    @Autowired
    MedicamentoInicializacaoService medicamentoInicializacaoService;

    @PostMapping
    public ResponseEntity<HttpStatus> inicialize() {

        if (farmaciaService.GetAll().isEmpty()) {
            List<Farmacia> farmacias = farmaciaInicializacaoService.inicializarDados();
            farmaciaService.SaveAll(farmacias);
        }

        if(medicamentoService.GetAll().isEmpty()) {
            List<Medicamento> medicamentos = medicamentoInicializacaoService.inicializarDados();
            medicamentoService.SaveAll(medicamentos);
        }

        return ResponseEntity.ok().build();
    };
}
