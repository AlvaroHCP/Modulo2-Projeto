package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.dto.*;
import com.devinhouse.DEVinPharmacy.service.EstoqueRepositoryService;
import com.devinhouse.DEVinPharmacy.service.FarmaciaRepositoryService;
import com.devinhouse.DEVinPharmacy.service.MedicamentoRepositoryService;
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
    public ResponseEntity<Object> estoqueAquisicao(@RequestBody @Valid @NotNull
                                              EstoqueRequest estoqueRequest){
        final Long cnpjRequest = estoqueRequest.getCnpj();
        final Integer nroRegistroRequest = estoqueRequest.getNroRegistro();
        final Integer quantidadeRequest = estoqueRequest.getQuantidade();

        estoqueRepoService.quantidadePositiva(quantidadeRequest);
        estoqueRepoService.cnpjAndNroRegistroExists(cnpjRequest, nroRegistroRequest);

        EstoqueAlteracaoResponse response = estoqueRepoService.GetByCnpjAndRegistro(cnpjRequest, nroRegistroRequest);
        boolean responseIsNull = response.getCnpj() == null;

        if(responseIsNull) {
            response = estoqueRepoService.Save(mapper.map(estoqueRequest, EstoqueAlteracaoResponse.class));
            return ResponseEntity.ok(response);
        }

        EstoqueAlteracaoResponse estoqueAtualizado = estoqueRepoService.aumentarEstoque(response, quantidadeRequest);
        return ResponseEntity.ok(estoqueAtualizado);
    };

    @DeleteMapping
    public ResponseEntity<Object> estoqueDelecao(@RequestBody @Valid @NotNull
                                              EstoqueRequest estoqueRequest){
        final Long cnpjRequest = estoqueRequest.getCnpj();
        final Integer nroRegistroRequest = estoqueRequest.getNroRegistro();
        final Integer quantidadeRequest = estoqueRequest.getQuantidade();

        estoqueRepoService.quantidadePositiva(quantidadeRequest);
//        if(! quantidadeResponse.getStatusCode().equals(HttpStatus.OK))
//            return quantidadeResponse;

        List<EstoqueResponse> estoqueResponse = estoqueRepoService.GetAllByCnpj(cnpjRequest);
        EstoqueAlteracaoResponse estoqueAlteracaoResponse =
                estoqueRepoService.GetByCnpjAndRegistro(cnpjRequest, nroRegistroRequest);

        ResponseEntity<Object> cnpjNroRegistroResponse = estoqueRepoService.cnpjNroRegistroExistentes(
                estoqueRequest, estoqueAlteracaoResponse);
        if(! cnpjNroRegistroResponse.getStatusCode().equals(HttpStatus.OK))
            return cnpjNroRegistroResponse;

        ResponseEntity<Object> reducaoPositiva = estoqueRepoService.estoqueResultantePositivo(
                estoqueAlteracaoResponse, quantidadeRequest);
        if(! reducaoPositiva.getStatusCode().equals(HttpStatus.OK)) {
            return reducaoPositiva;
        }
        EstoqueAlteracaoResponse estoqueAtualizado = estoqueRepoService.diminuirEstoque(
                estoqueAlteracaoResponse, quantidadeRequest);

        return ResponseEntity.ok(estoqueAtualizado);
    };

    @PutMapping
    public ResponseEntity<Object> estoqueTroca(@RequestBody @Valid @NotNull
                                               EstoqueTrocaRequest trocaRequest){

        List<EstoqueAlteracaoResponse> existeCnpjOrigem = estoqueRepoService.GetByCnpj(
                trocaRequest.getCnpjOrigem());
        if(existeCnpjOrigem.isEmpty())
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST, "CNPJ de Origem não existente!");

        List<EstoqueAlteracaoResponse> existeCnpjDestino = estoqueRepoService.GetByCnpj(
                trocaRequest.getCnpjDestino());
        if(existeCnpjDestino.isEmpty())
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST, "CNPJ de Destino não existente!");

        EstoqueRequest estoqueOrigem = new EstoqueRequest(
                trocaRequest.getCnpjOrigem(),
                trocaRequest.getNroRegistro(),
                trocaRequest.getQuantidade());
        EstoqueRequest estoqueDestino = new EstoqueRequest(
                trocaRequest.getCnpjDestino(),
                trocaRequest.getNroRegistro(),
                trocaRequest.getQuantidade());

        ResponseEntity<Object> deletado = estoqueDelecao(estoqueOrigem);
        if(deletado.getStatusCode().value() != HttpStatus.OK.value())
            return deletado;
        EstoqueAlteracaoResponse responseDeletado = mapper.map(deletado.getBody(), EstoqueAlteracaoResponse.class);

        ResponseEntity<Object> adicionado = estoqueAquisicao(estoqueDestino);
        if(adicionado.getStatusCode().value() != HttpStatus.OK.value())
            return adicionado;
        EstoqueAlteracaoResponse responseAdicionado = mapper.map(adicionado.getBody(), EstoqueAlteracaoResponse.class);

        EstoqueTrocaResponse trocaResponse = estoqueRepoService.trocaResponse( trocaRequest,
                 responseDeletado, responseAdicionado);

        return ResponseEntity.ok(trocaResponse);
    };
}
