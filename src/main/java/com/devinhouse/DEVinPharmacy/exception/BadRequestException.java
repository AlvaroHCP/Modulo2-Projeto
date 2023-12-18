package com.devinhouse.DEVinPharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestException extends RuntimeException{
    private String nome;
    private String mensagem;

    @Override
    public String toString() {
        return "'" + nome + "' " + mensagem +".";
    }
}
