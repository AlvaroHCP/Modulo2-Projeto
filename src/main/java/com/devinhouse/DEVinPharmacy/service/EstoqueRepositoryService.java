package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.connection.MyHttpResponse;
import com.devinhouse.DEVinPharmacy.dto.EstoqueAlteracaoResponse;
import com.devinhouse.DEVinPharmacy.dto.EstoqueResponse;
import com.devinhouse.DEVinPharmacy.model.Estoque;
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
    MedicamentoRepositoryService medicamentoRepoService;
    @Autowired
    ModelMapper mapper;

    public List<Estoque> GetAll(){
        return estoqueRepo.findAll();
    };

    public List<Estoque> GetEstoqueByCnpj(Long cnpj){
        return estoqueRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
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
        EstoqueAlteracaoResponse responseCnpjNroRegistro = new EstoqueAlteracaoResponse();
        List<Estoque> estoqueCnpj = GetEstoqueByCnpj(cnpj);
        if(estoqueCnpj.isEmpty()) {
            responseCnpjNroRegistro.setNroRegistro(nroRegistro);
            return responseCnpjNroRegistro;
        }
        List<Estoque> estoqueNroRegistro = GetEstoqueByNroRegistro(nroRegistro);
        if(estoqueNroRegistro.isEmpty()) {
            responseCnpjNroRegistro.setCnpj(cnpj);
            return responseCnpjNroRegistro;
        }
        responseCnpjNroRegistro.setCnpj(cnpj);
        List<Estoque> estoqueCnpjNroRegistro = estoqueCnpj.stream().filter(item ->
                item.getNroRegistro().equals(nroRegistro)).toList();
        if(estoqueCnpjNroRegistro.isEmpty()) {
            //TODO: criar o par
            responseCnpjNroRegistro.setCnpj(cnpj);
            responseCnpjNroRegistro.setNroRegistro(nroRegistro);
            return responseCnpjNroRegistro;
        }

        EstoqueAlteracaoResponse estoqueResponse = mapper.map(
                estoqueCnpjNroRegistro.get(0), EstoqueAlteracaoResponse.class);

        return estoqueResponse;
    };

    public Estoque Get(Long cnpj) {
        List<Estoque> estoques = estoqueRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
        if(estoques.isEmpty()) {
//            throw new NotFoundException(cnpj, "Registro Não encontrado.");
            Estoque estoque = new Estoque();
            return estoque;
        }

        return estoques.get(0);
    };

    public Estoque Save(Estoque estoque){
        estoque.setDataAtualizacao(LocalDateTime.now());
        return estoqueRepo.save(estoque);
    };
    public EstoqueAlteracaoResponse Save(EstoqueAlteracaoResponse estoque){
        estoque.setDataAtualizacao(LocalDateTime.now());
        Estoque estoqueDb = estoqueRepo.save(mapper.map(estoque, Estoque.class));
        EstoqueAlteracaoResponse estoqueAtualizado = mapper.map(estoqueDb, EstoqueAlteracaoResponse.class);
        return estoqueAtualizado;
    };

    public List<Estoque> SaveAll(List<Estoque> estoque){
        return estoqueRepo.saveAll(estoque);
    };

    public EstoqueAlteracaoResponse aumentarEstoque(EstoqueAlteracaoResponse aquisicao, Integer quantidade){
        aquisicao.setQuantidade(aquisicao.getQuantidade() + quantidade);
        return aquisicao;
    }

    public ResponseEntity<Object> quantidadePositiva(Integer quantidade){
        if(quantidade < 1)
            return MyHttpResponse.statusBody(HttpStatus.BAD_REQUEST,
                    "A Quantidade deve ser um número inteiro maior que zero!");
        System.out.println(ResponseEntity.ok().build().getStatusCode());
        System.out.println(HttpStatus.OK);
        return ResponseEntity.ok().build();
    };

}
