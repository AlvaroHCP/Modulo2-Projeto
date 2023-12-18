package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.dto.*;
import com.devinhouse.DEVinPharmacy.service.EstoqueRepositoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<EstoqueAlteracaoResponse> estoqueAquisicao(@RequestBody @Valid @NotNull
                                              EstoqueRequest estoqueRequest){
        final Long cnpjRequest = estoqueRequest.getCnpj();
        final Integer nroRegistroRequest = estoqueRequest.getNroRegistro();
        final Integer quantidadeRequest = estoqueRequest.getQuantidade();

        estoqueRepoService.quantidadePositiva(quantidadeRequest);
        estoqueRepoService.cnpjAndNroRegistroExists(cnpjRequest, nroRegistroRequest);

        EstoqueAlteracaoResponse response = estoqueRepoService.GetByCnpjAndRegistro(cnpjRequest, nroRegistroRequest);
        boolean cpfAndNroRegistroNotExists = response.getCnpj() == null;

        if(cpfAndNroRegistroNotExists) {
            response = estoqueRepoService.Save(mapper.map(estoqueRequest, EstoqueAlteracaoResponse.class));
            return ResponseEntity.ok(response);
        }

        EstoqueAlteracaoResponse estoqueAtualizado = estoqueRepoService.aumentarEstoque(response, quantidadeRequest);
        return ResponseEntity.ok(estoqueAtualizado);
    };

    @DeleteMapping
    public ResponseEntity<EstoqueAlteracaoResponse> estoqueDelecao(@RequestBody @Valid @NotNull
                                              EstoqueRequest estoqueRequest){
        final Long cnpjRequest = estoqueRequest.getCnpj();
        final Integer nroRegistroRequest = estoqueRequest.getNroRegistro();
        final Integer quantidadeRequest = estoqueRequest.getQuantidade();

        estoqueRepoService.quantidadePositiva(quantidadeRequest);
        estoqueRepoService.cnpjAndNroRegistroExists(cnpjRequest, nroRegistroRequest);

        EstoqueAlteracaoResponse response = estoqueRepoService.GetByCnpjAndRegistroToDelete(cnpjRequest, nroRegistroRequest);

        estoqueRepoService.estoqueResultantePositivo(response, quantidadeRequest);

        EstoqueAlteracaoResponse estoqueAtualizado = estoqueRepoService.diminuirEstoque(
                response, quantidadeRequest);

        return ResponseEntity.ok(estoqueAtualizado);
    };

    @PutMapping
    public ResponseEntity<Object> estoqueTroca(@RequestBody @Valid @NotNull
                                               EstoqueTrocaRequest trocaRequest){

        EstoqueAlteracaoResponse existeCnpjOrigem = estoqueRepoService.GetIfExistsByCnpj(
                trocaRequest.getCnpjOrigem(),"CNPJ de Origem!");

        EstoqueAlteracaoResponse existeCnpjDestino = estoqueRepoService.GetIfExistsByCnpj(
                trocaRequest.getCnpjDestino(),"CNPJ de Destino!");

        EstoqueRequest estoqueOrigem = new EstoqueRequest(
                trocaRequest.getCnpjOrigem(),
                trocaRequest.getNroRegistro(),
                trocaRequest.getQuantidade());
        EstoqueRequest estoqueDestino = new EstoqueRequest(
                trocaRequest.getCnpjDestino(),
                trocaRequest.getNroRegistro(),
                trocaRequest.getQuantidade());

        ResponseEntity<EstoqueAlteracaoResponse> deletado = estoqueDelecao(estoqueOrigem);
        EstoqueAlteracaoResponse responseDeletado = mapper.map(deletado.getBody(), EstoqueAlteracaoResponse.class);

        ResponseEntity<EstoqueAlteracaoResponse> adicionado = estoqueAquisicao(estoqueDestino);
        EstoqueAlteracaoResponse responseAdicionado = mapper.map(adicionado.getBody(), EstoqueAlteracaoResponse.class);

        EstoqueTrocaResponse trocaResponse = estoqueRepoService.trocaResponse( trocaRequest,
                 responseDeletado, responseAdicionado);

        return ResponseEntity.ok(trocaResponse);
    };
}
