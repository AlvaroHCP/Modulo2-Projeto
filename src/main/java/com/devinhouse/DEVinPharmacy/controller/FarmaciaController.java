package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.service.FarmaciaRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    public ResponseEntity<List<Farmacia>> farmaciasGet(){
        List<Farmacia> farmacias = farmaciaRepoService.GetAll();
        return MyHttpResponse.farmaciasOk(farmacias);
    };

    @GetMapping("/{cnpj}")
    public ResponseEntity<Farmacia> farmaciaByCNPJ(@PathVariable Long cnpj){
        Farmacia farmacia = farmaciaRepoService.Get(cnpj);
        return MyHttpResponse.farmaciaOk(farmacia);
    };
}
