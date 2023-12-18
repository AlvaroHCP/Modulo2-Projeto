package com.devinhouse.DEVinPharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    private String nome;
    private String registro;

    @Override
    public String toString() {
        return "Registro não encontrado para '" + nome +
                "' com Id = '" + registro +"'.";
    }
}
