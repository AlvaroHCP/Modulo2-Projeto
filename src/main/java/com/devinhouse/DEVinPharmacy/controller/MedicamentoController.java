package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.dto.MedicamentoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    @GetMapping
    public ResponseEntity<MedicamentoResponse> medicamentosGet(){

        return ResponseEntity.ok(new MedicamentoResponse());
    };
}
