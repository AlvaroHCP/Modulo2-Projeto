package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.dto.MedicamentoRequest;
import com.devinhouse.DEVinPharmacy.dto.MedicamentoResponse;
import com.devinhouse.DEVinPharmacy.exception.ApiAlreadyRegisteredException;
import com.devinhouse.DEVinPharmacy.exception.ApiNotFoundException;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import com.devinhouse.DEVinPharmacy.repository.MedicamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoRepositoryService {

    @Autowired
    MedicamentoRepository medicamentoRepo;
    @Autowired
    ModelMapper mapper;

    public void nroRegistroAlreadyRegistered(Integer nroRegistro){
        boolean medicamento = nroRegistroExists(nroRegistro);
        if(medicamento) {
            throw new ApiAlreadyRegisteredException(Medicamento.class.getSimpleName(), nroRegistro.toString());
        }
    };

    public boolean nroRegistroExists(Integer nroRegistro){
        return medicamentoRepo.existsById(nroRegistro);
    };

    public List<MedicamentoResponse> GetAll(){
        return medicamentoRepo.findAll().stream().map(item -> mapper.map(item, MedicamentoResponse.class)).toList();
    };

    public MedicamentoResponse Get(Integer registro) {
        List<Medicamento> medicamentos = medicamentoRepo.findAll()
                .stream().filter(item -> item.getNroRegistro().equals(registro)).toList();
        if(medicamentos.isEmpty())
            throw new ApiNotFoundException(Medicamento.class.getSimpleName(), registro.toString());

        return mapper.map(medicamentos.get(0),MedicamentoResponse.class);
    };

    public MedicamentoResponse Save(MedicamentoRequest medicamentoRequest){
        Medicamento request = mapper.map(medicamentoRequest, Medicamento.class);
        return mapper.map(medicamentoRepo.save(request), MedicamentoResponse.class);
    };

    public List<Medicamento> SaveAll(List<Medicamento> medicamentos){
        return medicamentoRepo.saveAll(medicamentos);
    };

}
