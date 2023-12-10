package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.dto.EstoqueAquisicaoResponse;
import com.devinhouse.DEVinPharmacy.dto.EstoqueRequest;
import com.devinhouse.DEVinPharmacy.dto.EstoqueResponse;
import com.devinhouse.DEVinPharmacy.service.EstoqueRepositoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping
    public ResponseEntity<?> estoqueAquisicao(@RequestBody @Valid @NotNull
                                              EstoqueRequest estoqueRequest){
        //FIXME: retornar erro com mensagem pra os RN01, RN02, e RN03
        //TODO: Caso não hava cnpj e nroRegistro, criar o registro em Estoque
        if(estoqueRequest.getNroRegistro() < 1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número de Registro deve ser maior que zero e inteiro!");
        EstoqueAquisicaoResponse estoqueAquisicaoResponse =
                estoqueRepoService.GetAllByCnpjAndRegistro(
                        estoqueRequest.getCnpj(),estoqueRequest.getNroRegistro());
        if(estoqueAquisicaoResponse.getCnpj() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ ou Número de Registro não existentes!");
        EstoqueAquisicaoResponse estoqueAtualizado = estoqueRepoService.aumentarEstoque(
                estoqueAquisicaoResponse, estoqueRequest.getQuantidade());
        EstoqueAquisicaoResponse estoqueSalvo = estoqueRepoService.Save(estoqueAtualizado);
        return ResponseEntity.ok(estoqueSalvo);
    };
}
