package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.repository.FarmaciaRepository;
import com.devinhouse.DEVinPharmacy.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoRepositoryService {

    @Autowired
    MedicamentoRepository medicamentoRepo;

    public List<Medicamento> GetAll(){
        return medicamentoRepo.findAll();
    };
//    public Medicamento Get(Medicamento farmacia){
//        return farmaciaRepo.findById(farmacia.getCnpj());
//    };

    public Medicamento Save(Medicamento farmacia){
        return medicamentoRepo.save(farmacia);
    };

    public List<Medicamento> SaveAll(List<Medicamento> farmacias){
        return medicamentoRepo.saveAll(farmacias);
    };

}
