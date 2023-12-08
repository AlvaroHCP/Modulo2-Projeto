package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.repository.FarmaciaRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FarmaciaRepositoryService {

    @Autowired
    FarmaciaRepository farmaciaRepo;

    public List<Farmacia> GetAll(){
        return farmaciaRepo.findAll();
    };
    public Farmacia Get(Long cnpj) throws BadRequestException {
//        Optional<Farmacia> cnpjQuery = farmaciaRepo.findById(cnpj);
//        return cnpjQuery.get();
//        Optional<Farmacia> cnpjQuery = farmaciaRepo.findById(cnpj).orElseThrow(new ObjetoNaoEncontradoException);

//        Farmacia farmacia = farmaciaRepo.findAll()
//                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList().get(0);

        //FIXME: replace the error by a 404 with message to the user.
        Farmacia farmacia = farmaciaRepo.findById(cnpj).orElseThrow(() ->
//                new ChangeSetPersister.NotFoundException()
                new BadRequestException(cnpj + " não encontrado!")
                );
        return farmacia;
    };

    public Farmacia Save(Farmacia farmacia){
        return farmaciaRepo.save(farmacia);
    };

    public List<Farmacia> SaveAll(List<Farmacia> farmacias){
        return farmaciaRepo.saveAll(farmacias);
    };

}
