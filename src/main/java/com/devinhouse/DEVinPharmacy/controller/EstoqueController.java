package com.devinhouse.DEVinPharmacy.controller;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.dto.EstoqueAlteracaoResponse;
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

        ResponseEntity<Object> quantidadeResponse = estoqueRepoService.quantidadePositiva(
                quantidadeRequest);
        if(! quantidadeResponse.getStatusCode().equals(HttpStatus.OK))
            return quantidadeResponse;

        List<EstoqueResponse> estoqueResponse = estoqueRepoService.GetAllByCnpj(cnpjRequest);
        EstoqueAlteracaoResponse estoqueAlteracaoResponse =
                estoqueRepoService.GetByCnpjAndRegistro(cnpjRequest, nroRegistroRequest);

        ResponseEntity<Object> cnpjNroRegistroResponse = estoqueRepoService.cnpjNroRegistroExistentes(
                estoqueRequest, estoqueAlteracaoResponse);
        if(cnpjNroRegistroResponse.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            EstoqueAlteracaoResponse estoqueCriado = estoqueRepoService.Save(
                    mapper.map(estoqueRequest, EstoqueAlteracaoResponse.class)
            );
            return ResponseEntity.ok(estoqueCriado);
        };
        if(! cnpjNroRegistroResponse.getStatusCode().equals(HttpStatus.OK))
            return cnpjNroRegistroResponse;

        EstoqueAlteracaoResponse estoqueAtualizado = estoqueRepoService.aumentarEstoque(
                estoqueAlteracaoResponse, quantidadeRequest);
        return ResponseEntity.ok(estoqueAtualizado);
    };

    @DeleteMapping
    public ResponseEntity<Object> estoqueDelecao(@RequestBody @Valid @NotNull
                                              EstoqueRequest estoqueRequest){
        final Long cnpjRequest = estoqueRequest.getCnpj();
        final Integer nroRegistroRequest = estoqueRequest.getNroRegistro();
        final Integer quantidadeRequest = estoqueRequest.getQuantidade();

        ResponseEntity<Object> quantidadeResponse = estoqueRepoService.quantidadePositiva(
                quantidadeRequest);
        if(! quantidadeResponse.getStatusCode().equals(HttpStatus.OK))
            return quantidadeResponse;

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
}
