package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Medicamento;
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

    public Medicamento Get(Integer registro) {
        List<Medicamento> medicamentos = medicamentoRepo.findAll()
                .stream().filter(item -> item.getNroRegistro().equals(registro)).toList();
        if(medicamentos.isEmpty()) {
//            throw new NotFoundException(registro, "Registro Não encontrado.");
            Medicamento medicamento = new Medicamento();
            return medicamento;
        }

        return medicamentos.get(0);
    };

    public Medicamento Save(Medicamento medicamento){
        return medicamentoRepo.save(medicamento);
    };

    public List<Medicamento> SaveAll(List<Medicamento> medicamentos){
        return medicamentoRepo.saveAll(medicamentos);
    };

}
