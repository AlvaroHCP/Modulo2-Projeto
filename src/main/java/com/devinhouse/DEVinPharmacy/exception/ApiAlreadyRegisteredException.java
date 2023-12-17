package com.devinhouse.DEVinPharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiAlreadyRegisteredException extends RuntimeException {
    private String nome;
    private String registro;

    @Override
    public String toString() {
        return "Registro já existente para " + nome +
                " = " + registro +".";
    }
}