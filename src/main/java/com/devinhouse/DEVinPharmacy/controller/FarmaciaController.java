package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.dto.FarmaciaRequest;
import com.devinhouse.DEVinPharmacy.dto.FarmaciaResponse;
import com.devinhouse.DEVinPharmacy.exception.ApiNotFoundException;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.service.FarmaciaRepositoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {
    @Autowired
    FarmaciaRepositoryService farmaciaRepoService;

    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> farmaciasGet(){
        List<FarmaciaResponse> farmacias = farmaciaRepoService.GetAll();
        return MyHttpResponse.farmaciasOk(farmacias);
    };

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaResponse> farmaciaByCNPJ(@PathVariable Long cnpj){
        FarmaciaResponse farmacia = farmaciaRepoService.Get(cnpj);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(farmacia);
    };

    @PostMapping
    public ResponseEntity<FarmaciaResponse> inserirFarmacia(
            @RequestBody @Valid @NotNull FarmaciaRequest farmaciaRequest){
        Long cnpj = farmaciaRequest.getCnpj();
        farmaciaRepoService.cnpjAlreadyRegistered(cnpj);

        FarmaciaResponse farmaciaResponse = farmaciaRepoService.Save(farmaciaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciaResponse);
    }
}
