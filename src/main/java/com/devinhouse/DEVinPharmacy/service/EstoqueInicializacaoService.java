package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.model.Estoque;
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

        Estoque estoque2 = new Estoque(
                90561736000121L,
                7473,
                10,
                LocalDateTime.now()
        );

        Estoque estoque3 = new Estoque(
                43178995000198L,
                7473,
                2,
                LocalDateTime.now()
        );

        Estoque estoque4 = new Estoque(
                43178995000198L,
                2233,
                15,
                LocalDateTime.now()
        );

        Estoque estoque5 = new Estoque(
                43178995000198L,
                8880,
                16,
                LocalDateTime.now()
        );

        Estoque estoque6 = new Estoque(
                43178995000198L,
                4040,
                22,
                LocalDateTime.now()
        );

        return List.of(estoque1,
                estoque2,
                estoque3,
                estoque4,
                estoque5,
                estoque6);
    };
}
