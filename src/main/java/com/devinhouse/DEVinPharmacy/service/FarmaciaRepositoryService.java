package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaRepositoryService {

    @Autowired
    FarmaciaRepository farmaciaRepo;

    public List<Farmacia> GetAll(){
        return farmaciaRepo.findAll();
    };
    public Farmacia Get(Long cnpj){
//        Optional<Farmacia> cnpjQuery = farmaciaRepo.findById(cnpj);
//        return cnpjQuery.get();
//        Optional<Farmacia> cnpjQuery = farmaciaRepo.findById(cnpj).orElseThrow(new ObjetoNaoEncontradoException);
        Farmacia farmacia = farmaciaRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList().get(0);
        return farmacia;
    };

    public Farmacia Save(Farmacia farmacia){
        return farmaciaRepo.save(farmacia);
    };

    public List<Farmacia> SaveAll(List<Farmacia> farmacias){
        return farmaciaRepo.saveAll(farmacias);
    };

}
