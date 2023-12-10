package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
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
        final Long cnpjRequest = estoqueRequest.getCnpj();
        final Integer nroRegistroRequest = estoqueRequest.getNroRegistro();
        final Integer quantidadeRequest = estoqueRequest.getQuantidade();

        if(quantidadeRequest < 1)
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST,
                    "A Quantidade deve ser um número inteiro maior que zero!");
        List<EstoqueResponse> estoqueResponse = estoqueRepoService.GetAllByCnpj(cnpjRequest);
        EstoqueAquisicaoResponse estoqueAquisicaoResponse =
                estoqueRepoService.GetByCnpjAndRegistro(cnpjRequest, nroRegistroRequest);

        if(estoqueAquisicaoResponse.getCnpj() == null)
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST, "CNPJ não existente!");

        if(estoqueAquisicaoResponse.getNroRegistro() == null)
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST, "Número de Registro não existente!");

        if(estoqueAquisicaoResponse.getQuantidade() == null && estoqueAquisicaoResponse.getDataAtualizacao() == null) {
            EstoqueAquisicaoResponse estouqeCriado = estoqueRepoService.Save(
                    mapper.map(estoqueRequest, EstoqueAquisicaoResponse.class)
            );
            return ResponseEntity.ok(estouqeCriado);
        };

        EstoqueAquisicaoResponse estoqueAtualizado = estoqueRepoService.aumentarEstoque(
                estoqueAquisicaoResponse, quantidadeRequest);
        EstoqueAquisicaoResponse estoqueSalvo = estoqueRepoService.Save(estoqueAtualizado);
        return ResponseEntity.ok(estoqueSalvo);
    };
}
