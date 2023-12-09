package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.dto.FarmaciaResponse;
import com.devinhouse.DEVinPharmacy.exception.ApiNotFoundException;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.service.FarmaciaRepositoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> farmaciasGet(){
        List<Farmacia> farmacias = farmaciaRepoService.GetAll();

        List<FarmaciaResponse> farmaciaResponse = farmacias.stream().map(farmacia ->
                mapper.map(farmacia, FarmaciaResponse.class)
        ).toList();
        return MyHttpResponse.farmaciasOk(farmaciaResponse);
    };

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> farmaciaByCNPJ(@PathVariable Long cnpj){
        Farmacia farmacia = farmaciaRepoService.Get(cnpj);
        if(farmacia.getCnpj() != null) {
            FarmaciaResponse farmaciaResponse = mapper.map(farmacia, FarmaciaResponse.class);
            return MyHttpResponse.farmaciaOk(farmaciaResponse);
        }
        ApiNotFoundException notFound = new ApiNotFoundException(cnpj, "Registro Não encontrado.");
//        FarmaciaResponse farmaciaResponse = mapper.map(notFound, FarmaciaResponse.class);
//        FarmaciaResponse farmaciaResponse = mapper.map(farmacia, FarmaciaResponse.class);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
    };
}
