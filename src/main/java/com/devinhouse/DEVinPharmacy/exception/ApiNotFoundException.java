package com.devinhouse.DEVinPharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiNotFoundException extends RuntimeException {
    private String nome;
    private String registro;

    @Override
    public String toString() {
        return "Registro não encontrado para " + nome +
                " = " + registro +".";
    }
}
