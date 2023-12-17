package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.exception.ApiNotFoundException;
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
    public Farmacia Get(Long cnpj) {
        List<Farmacia> farmacias = farmaciaRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
        if(farmacias.isEmpty()) {
            throw new ApiNotFoundException(Farmacia.class.getSimpleName(), "Registro Não encontrado.");
//            Farmacia farmacia = new Farmacia();
//            farmacia.setCnpj(cnpj);
//            return farmacia;
        }

        return farmacias.get(0);

        //FIXME: replace the error by a 404 with message to the user.
//        Farmacia farmacia = farmaciaRepo.findById(cnpj).orElseThrow(() ->
//                new NotFoundException(
//                        cnpj,
//                        "Registro Não encontrado.")
//                );

//        Farmacia farmacia = farmaciaRepo.findById(cnpj).orElse();
//        return farmacia;
    };

    public Farmacia Save(Farmacia farmacia){
        return farmaciaRepo.save(farmacia);
    };

    public List<Farmacia> SaveAll(List<Farmacia> farmacias){
        return farmaciaRepo.saveAll(farmacias);
    };

}
