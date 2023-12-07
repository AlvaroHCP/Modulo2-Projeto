package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Endereco;
import com.devinhouse.DEVinPharmacy.model.Estoque;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueInicializacaoService {

    public List<Estoque> inicializarDados(){
        Estoque estoque1 = new Estoque(
                90561736000121L,
                1010,
                12,
                LocalDateTime.now()
        );
        return List.of(estoque1);
    };
}
