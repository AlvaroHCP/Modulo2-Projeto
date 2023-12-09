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

    @PostMapping
    public ResponseEntity<?> inserirFarmacia(
            @RequestBody @Valid @NotNull FarmaciaRequest farmaciaRequest){

        Farmacia farmacia = farmaciaRepoService.Get(farmaciaRequest.getCnpj());
        if(farmacia.getCnpj() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiNotFoundException(farmaciaRequest.getCnpj(),
                            "Esta farmácia já existe, ou os seus dados estão inválidos!")
            );
        }
        FarmaciaResponse farmaciaResponse = mapper.map(
                farmaciaRepoService.Save(
                        mapper.map(farmaciaRequest, Farmacia.class)
                ), FarmaciaResponse.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciaResponse);
    }
}
