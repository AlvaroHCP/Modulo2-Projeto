package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Estoque;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.repository.EstoqueRepository;
import com.devinhouse.DEVinPharmacy.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueRepositoryService {

    @Autowired
    EstoqueRepository estoqueRepo;

    public List<Estoque> GetAll(){
        return estoqueRepo.findAll();
    };
//    public Estoque Get(Estoque estoque){
//        return estoqueRepo.findById(estoque.getCnpj());
//    };

    public Estoque Save(Estoque estoque){
        return estoqueRepo.save(estoque);
    };

    public List<Estoque> SaveAll(List<Estoque> estoque){
        return estoqueRepo.saveAll(estoque);
    };

}
