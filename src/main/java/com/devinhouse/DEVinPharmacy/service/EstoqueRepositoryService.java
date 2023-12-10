package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.dto.EstoqueAquisicaoResponse;
import com.devinhouse.DEVinPharmacy.dto.EstoqueResponse;
import com.devinhouse.DEVinPharmacy.model.Estoque;
import com.devinhouse.DEVinPharmacy.repository.EstoqueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public EstoqueAquisicaoResponse GetByCnpjAndRegistro(Long cnpj, Integer nroRegistro){
        EstoqueAquisicaoResponse responseCnpjNroRegistro = new EstoqueAquisicaoResponse();
        List<Estoque> estoqueCnpj = GetEstoqueByCnpj(cnpj);
        List<Estoque> estoqueNroRegistro = GetEstoqueByNroRegistro(nroRegistro);
        if(estoqueCnpj.isEmpty()){
            if(estoqueNroRegistro.isEmpty())
                return responseCnpjNroRegistro;
            responseCnpjNroRegistro.setNroRegistro(nroRegistro);
            return responseCnpjNroRegistro;
        }
        responseCnpjNroRegistro.setCnpj(cnpj);
        if(estoqueNroRegistro.isEmpty())
            return responseCnpjNroRegistro;
        Estoque estoqueCnpjNroRegistro = estoqueCnpj.stream().filter(item ->
                item.getNroRegistro().equals(nroRegistro)).toList().get(0);

        EstoqueAquisicaoResponse estoqueResponse = mapper.map(
                estoqueCnpjNroRegistro, EstoqueAquisicaoResponse.class);

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
    public EstoqueAquisicaoResponse Save(EstoqueAquisicaoResponse estoque){
        estoque.setDataAtualizacao(LocalDateTime.now());
        Estoque estoqueDb = estoqueRepo.save(mapper.map(estoque, Estoque.class));
        EstoqueAquisicaoResponse estoqueAtualizado = mapper.map(estoqueDb, EstoqueAquisicaoResponse.class);
        return estoqueAtualizado;
    };

    public List<Estoque> SaveAll(List<Estoque> estoque){
        return estoqueRepo.saveAll(estoque);
    };

    public EstoqueAquisicaoResponse aumentarEstoque(EstoqueAquisicaoResponse aquisicao, Integer quantidade){
        aquisicao.setQuantidade(aquisicao.getQuantidade() + quantidade);
        return aquisicao;
    }

}
