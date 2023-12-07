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

    public Farmacia farmaciaSave(Farmacia farmacia){
        return farmaciaRepo.save(farmacia);
    };

    public List<Farmacia> farmaciaSave(List<Farmacia> farmacias){
        return farmaciaRepo.saveAll(farmacias);
    };

}
