package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.dto.FarmaciaResponse;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.service.FarmaciaRepositoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {
    @Autowired
    FarmaciaRepositoryService farmaciaRepoService;
    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> farmaciasGet(){
        List<Farmacia> farmacias = farmaciaRepoService.GetAll();
        List<FarmaciaResponse> farmaciaResponse = farmacias.stream().map(
                farmacia -> new FarmaciaResponse(
                        farmacia.getCnpj(),
                        farmacia.getRazaoSocial(),
                        farmacia.getNomeFantasia(),
                        farmacia.getEmail(),
                        farmacia.getTelefone(),
                        farmacia.getCelular(),
                        farmacia.getEndereco())
        ).toList();
        return MyHttpResponse.farmaciasOk(farmaciaResponse);
    };

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaResponse> farmaciaByCNPJ(@PathVariable Long cnpj){
        try {
            Farmacia farmacia = farmaciaRepoService.Get(cnpj);
            FarmaciaResponse farmaciaResponse = new FarmaciaResponse(
                    farmacia.getCnpj(),
                    farmacia.getRazaoSocial(),
                    farmacia.getNomeFantasia(),
                    farmacia.getEmail(),
                    farmacia.getTelefone(),
                    farmacia.getCelular(),
                    farmacia.getEndereco());
            return MyHttpResponse.farmaciaOk(farmaciaResponse);
        } catch (BadRequestException e) {
            FarmaciaResponse farmaciaResponse = new FarmaciaResponse();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(farmaciaResponse);
        }

    };
}
