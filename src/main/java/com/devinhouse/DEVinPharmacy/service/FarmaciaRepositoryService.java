package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.dto.FarmaciaResponse;
import com.devinhouse.DEVinPharmacy.exception.ApiAlreadyRegisteredException;
import com.devinhouse.DEVinPharmacy.exception.ApiNotFoundException;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.repository.FarmaciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmaciaRepositoryService {

    @Autowired
    FarmaciaRepository farmaciaRepo;
    @Autowired
    ModelMapper mapper;

//    public List<Farmacia> GetAll(){
//        return farmaciaRepo.findAll();
//    };
    public List<FarmaciaResponse> GetAll(){
        return farmaciaRepo.findAll().stream().map(item -> mapper.map(item, FarmaciaResponse.class)).toList();
    };
    public FarmaciaResponse Get(Long cnpj) {
        List<Farmacia> farmacias = farmaciaRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
        if(farmacias.isEmpty()) {
            throw new ApiNotFoundException("Cnpj", cnpj.toString());
        }
        return mapper.map(farmacias.get(0), FarmaciaResponse.class);
    };

    public void cnpjAlreadyRegistered(Long cnpj){
        Optional<Farmacia> farmacia = farmaciaRepo.findById(cnpj);
        if(farmacia.isPresent()) {
            throw new ApiAlreadyRegisteredException("Cnpj", cnpj.toString());
        }
    };

    public Farmacia Save(Farmacia farmacia){
        return farmaciaRepo.save(farmacia);
    };

    public List<Farmacia> SaveAll(List<Farmacia> farmacias){
        return farmaciaRepo.saveAll(farmacias);
    };

}
