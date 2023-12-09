package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.dto.MedicamentoResponse;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.service.MedicamentoRepositoryService;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    @Autowired
    MedicamentoRepositoryService medicamentoRepoService;
    @Autowired
    ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> medicamentosGet(){
        List<Medicamento> medicamentos = medicamentoRepoService.GetAll();
        List<MedicamentoResponse> response = medicamentos.stream().map(item ->
                mapper.map(item, MedicamentoResponse.class)
        ).toList();
        return ResponseEntity.ok(response);
    };
}
