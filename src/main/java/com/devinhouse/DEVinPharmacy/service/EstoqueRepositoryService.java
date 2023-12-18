package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.dto.*;
import com.devinhouse.DEVinPharmacy.exception.ApiBadRequestException;
import com.devinhouse.DEVinPharmacy.exception.ApiNotFoundException;
import com.devinhouse.DEVinPharmacy.model.Estoque;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.repository.EstoqueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueRepositoryService {

    @Autowired
    EstoqueRepository estoqueRepo;
    @Autowired
    FarmaciaRepositoryService farmaciaRepoService;
    @Autowired
    MedicamentoRepositoryService medicamentoRepoService;
    @Autowired
    ModelMapper mapper;

    public boolean cnpjAndNroRegistroExists(Long cnpj, Integer nroRegistro){
        boolean cnpjExists = farmaciaRepoService.cnpjExists(cnpj);
        if(!cnpjExists)
            throw new ApiNotFoundException(Farmacia.class.getSimpleName(), cnpj.toString());

        boolean nroRegistroExists = medicamentoRepoService.nroRegistroExists(nroRegistro);
        if(!nroRegistroExists)
            throw new ApiNotFoundException(Medicamento.class.getSimpleName(), nroRegistro.toString());
        return true;
    };

    public List<Estoque> GetAll(){
        return estoqueRepo.findAll();
    };

    public List<Estoque> GetEstoqueByCnpj(Long cnpj){
        return estoqueRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
    };
    public List<EstoqueAlteracaoResponse> GetByCnpj(Long cnpj){
        List<Estoque> consulta = estoqueRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
        return consulta.stream().map(item -> mapper.map(item, EstoqueAlteracaoResponse.class)).toList();
    };

    public List<Estoque> GetEstoqueByNroRegistro(Integer nroRegistro){
        return estoqueRepo.findAll()
                .stream().filter(item -> item.getNroRegistro().equals(nroRegistro)).toList();
    };

    public List<EstoqueResponse> GetAllByCnpj(Long cnpj){
        List<EstoqueResponse> estoqueResponse = GetEstoqueByCnpj(cnpj).stream().map(item ->
            mapper.map(item, EstoqueResponse.class)
        ).toList();

        estoqueResponse.forEach(item -> {
            item.setNome(
                    medicamentoRepoService.Get(item.getNroRegistro()).getNome()
            );
        });
        return estoqueResponse;
    };

    public EstoqueAlteracaoResponse GetByCnpjAndRegistro(Long cnpj, Integer nroRegistro){
        List<Estoque> estoqueCnpj = GetEstoqueByCnpj(cnpj);
        List<Estoque> estoqueCnpjNroRegistro = estoqueCnpj.stream().filter(item ->
                item.getNroRegistro().equals(nroRegistro)).toList();
        if(estoqueCnpjNroRegistro.isEmpty())
            return new EstoqueAlteracaoResponse();

        EstoqueAlteracaoResponse estoqueResponse = mapper.map(
                estoqueCnpjNroRegistro.get(0), EstoqueAlteracaoResponse.class);
        return estoqueResponse;
    };

    public Estoque Get(Long cnpj) {
        List<Estoque> estoques = estoqueRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
        if(estoques.isEmpty())
            throw new ApiNotFoundException(Estoque.class.getSimpleName(), cnpj.toString());
        return estoques.get(0);
    };

    public EstoqueAlteracaoResponse Save(EstoqueRequest estoqueRequest){
        Estoque estoque = mapper.map(estoqueRequest, Estoque.class);
        estoque.setDataAtualizacao(LocalDateTime.now());
        return Save(mapper.map(estoque, EstoqueAlteracaoResponse.class));
    };
    public EstoqueAlteracaoResponse Save(EstoqueAlteracaoResponse estoque){
        estoque.setDataAtualizacao(LocalDateTime.now());
        Estoque estoqueDb = estoqueRepo.save(mapper.map(estoque, Estoque.class));
        EstoqueAlteracaoResponse estoqueAtualizado = mapper.map(estoqueDb, EstoqueAlteracaoResponse.class);
        return estoqueAtualizado;
    };
    public void Delete(EstoqueAlteracaoResponse estoque){
        estoque.setDataAtualizacao(LocalDateTime.now());
        estoqueRepo.delete(mapper.map(estoque, Estoque.class));
    };

    public List<Estoque> SaveAll(List<Estoque> estoque){
        return estoqueRepo.saveAll(estoque);
    };

    public EstoqueAlteracaoResponse aumentarEstoque(EstoqueAlteracaoResponse aquisicao, Integer quantidade){
        aquisicao.setQuantidade(aquisicao.getQuantidade() + quantidade);
        EstoqueAlteracaoResponse atualizacao = Save(aquisicao);
        return atualizacao;
    }
    public EstoqueAlteracaoResponse diminuirEstoque(EstoqueAlteracaoResponse aquisicao, Integer quantidade){
        Integer subtracao = aquisicao.getQuantidade() - quantidade;
        aquisicao.setQuantidade(subtracao);

        if(subtracao == 0) {
            Delete(aquisicao);
        } else {
            EstoqueAlteracaoResponse atualizacao = Save(aquisicao);
            return atualizacao;
        }
        return aquisicao;
    }
    public ResponseEntity<Object> estoqueResultantePositivo(EstoqueAlteracaoResponse aquisicao, Integer quantidade){
        int estoqueResultante = aquisicao.getQuantidade() - quantidade;
        if(estoqueResultante < 0)
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST, "Quantidade a ser vendida é maior que o estoque atual!");
        return ResponseEntity.ok().build();
    }

    public void quantidadePositiva(Integer quantidade){
        if(quantidade < 1)
            throw new ApiBadRequestException("quantidade", "deve ser um número inteiro maior que zero!");
    };

    public ResponseEntity<Object> cnpjNroRegistroExistentes(EstoqueRequest request, EstoqueAlteracaoResponse response){
        if(response.getCnpj() == null)
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST, "CNPJ não existente!");

        if(response.getNroRegistro() == null)
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST, "Número de Registro não existente!");

        if(response.getQuantidade() == null && response.getDataAtualizacao() == null) {
            return MyHttpResponse.statusBody(HttpStatus.NOT_FOUND, "Número de Registro não encontrado para este Cnpj");
        };
        return ResponseEntity.ok().build();
    };

    public EstoqueTrocaResponse trocaResponse(EstoqueTrocaRequest trocaRequest,
                                              EstoqueAlteracaoResponse responseDeletado,
                                              EstoqueAlteracaoResponse responseAdicionado){
        EstoqueTrocaResponse trocaResponse = mapper.map(trocaRequest, EstoqueTrocaResponse.class);
        trocaResponse.setQuantidadeOrigem(responseDeletado.getQuantidade());
        trocaResponse.setQuantidadeDestino(responseAdicionado.getQuantidade());
        return trocaResponse;
    };
}
