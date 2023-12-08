package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
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
    FarmaciaRepositoryService farmaciaService;
    @Autowired
    FarmaciaInicializacaoService farmaciaInicializacaoService;
    @Autowired
    MedicamentoRepositoryService medicamentoService;
    @Autowired
    MedicamentoInicializacaoService medicamentoInicializacaoService;
    @Autowired
    EstoqueRepositoryService estoqueService;
    @Autowired
    EstoqueInicializacaoService estoqueInicializacaoService;

    @PostMapping
    public ResponseEntity<?> inicialize() {

        if (farmaciaService.GetAll().isEmpty()) {
            List<Farmacia> farmacias = farmaciaInicializacaoService.inicializarDados();
            farmaciaService.SaveAll(farmacias);
        }

        if(medicamentoService.GetAll().isEmpty()) {
            List<Medicamento> medicamentos = medicamentoInicializacaoService.inicializarDados();
            medicamentoService.SaveAll(medicamentos);
        }

        if(estoqueService.GetAll().isEmpty()) {
            List<Estoque> estoques = estoqueInicializacaoService.inicializarDados();
            estoqueService.SaveAll(estoques);
        }

        return MyHttpResponse.ok();
    };
}
