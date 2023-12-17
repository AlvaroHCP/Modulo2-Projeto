package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.dto.MedicamentoRequest;
import com.devinhouse.DEVinPharmacy.dto.MedicamentoResponse;
import com.devinhouse.DEVinPharmacy.exception.ApiNotFoundException;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.service.MedicamentoRepositoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
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

    @PostMapping
    public ResponseEntity<?> inserirMedicamento(
            @RequestBody @Valid @NotNull MedicamentoRequest medicamentoRequest){
        Medicamento medicamento = medicamentoRepoService.Get(medicamentoRequest.getNroRegistro());
        if(medicamento.getNroRegistro() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiNotFoundException("nroRegistro",
                            "Este medicamento já existe, ou os seus dados estão inválidos!")
            );
        }
        Medicamento medicamentoDb = medicamentoRepoService.Save(
                mapper.map(
                        medicamentoRequest, Medicamento.class
                )
        );
        MedicamentoResponse medicamentoResponse = mapper.map(
                medicamentoDb, MedicamentoResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoResponse);

//        return ResponseEntity.status(HttpStatus.CREATED).body(
//"""
//{
//\tstatus: Created,\s
//\tmessage: Deu bom!
//}
//"""
//        );
    };
}
