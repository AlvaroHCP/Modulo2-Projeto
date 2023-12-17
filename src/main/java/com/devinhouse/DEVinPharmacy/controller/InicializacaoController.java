package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.model.Estoque;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inicializacao")
public class InicializacaoController {

    @Autowired
    FarmaciaRepositoryService farmaciaRepoService;
    @Autowired
    FarmaciaInicializacaoService farmaciaInicializacaoService;
    @Autowired
    MedicamentoRepositoryService medicamentoRepoService;
    @Autowired
    MedicamentoInicializacaoService medicamentoInicializacaoService;
    @Autowired
    EstoqueRepositoryService estoqueRepoService;
    @Autowired
    EstoqueInicializacaoService estoqueInicializacaoService;

    @PostMapping
    public ResponseEntity<Object> inicialize() {

        if (farmaciaRepoService.GetAll().isEmpty()) {
            List<Farmacia> farmacias = farmaciaInicializacaoService.inicializarDados();
            farmaciaRepoService.SaveAll(farmacias);
        }

        if(medicamentoRepoService.GetAll().isEmpty()) {
            List<Medicamento> medicamentos = medicamentoInicializacaoService.inicializarDados();
            medicamentoRepoService.SaveAll(medicamentos);
        }

        if(estoqueRepoService.GetAll().isEmpty()) {
            List<Estoque> estoques = estoqueInicializacaoService.inicializarDados();
            estoqueRepoService.SaveAll(estoques);
        }

        return ResponseEntity.ok().build();
    };
}
