package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.dto.MedicamentoRequest;
import com.devinhouse.DEVinPharmacy.dto.MedicamentoResponse;
import com.devinhouse.DEVinPharmacy.service.MedicamentoRepositoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    @Autowired
    MedicamentoRepositoryService medicamentoRepoService;

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> medicamentosGet(){
        List<MedicamentoResponse> response = medicamentoRepoService.GetAll();
        return ResponseEntity.ok(response);
    };

    @PostMapping
    public ResponseEntity<MedicamentoResponse> inserirMedicamento(
            @RequestBody @Valid @NotNull MedicamentoRequest medicamentoRequest){
        int nroRegistro = medicamentoRequest.getNroRegistro();
        medicamentoRepoService.nroRegistroAlreadyRegistered(nroRegistro);

        MedicamentoResponse medicamentoResponse = medicamentoRepoService.Save(medicamentoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoResponse);
    };
}
