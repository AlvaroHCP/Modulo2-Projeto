package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.dto.EstoqueResponse;
import com.devinhouse.DEVinPharmacy.service.EstoqueRepositoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueRepositoryService estoqueRepoService;
    @Autowired
    ModelMapper mapper;

    @GetMapping("{cnpj}")
    public ResponseEntity<List<EstoqueResponse>> estoqueGet(@PathVariable Long cnpj){
        List<EstoqueResponse> estoqueReponse = estoqueRepoService.GetAllByCnpj(cnpj);
        return ResponseEntity.ok(estoqueReponse);
    };
}
