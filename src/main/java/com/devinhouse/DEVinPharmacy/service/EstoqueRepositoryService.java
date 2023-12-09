package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.dto.EstoqueResponse;
import com.devinhouse.DEVinPharmacy.model.Estoque;
import com.devinhouse.DEVinPharmacy.repository.EstoqueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<EstoqueResponse> GetAllByCnpj(Long cnpj){
        List<Estoque> estoqueTotal = estoqueRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
        List<EstoqueResponse> estoqueResponse = estoqueTotal.stream().map(item ->
            mapper.map(item, EstoqueResponse.class)
        ).toList();

        estoqueResponse.forEach(item -> {
            item.setNome(
                    medicamentoRepoService.Get(item.getNroRegistro()).getNome()
            );
        });
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
        return estoqueRepo.save(estoque);
    };

    public List<Estoque> SaveAll(List<Estoque> estoque){
        return estoqueRepo.saveAll(estoque);
    };

}
