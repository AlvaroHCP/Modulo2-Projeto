package com.devinhouse.DEVinPharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiNotFoundException {
    private Long cnpj;
    private String message;

    @Override
    public String toString() {
        return "{" +
                "cnpj=" + cnpj +
                ", message='" + message + '\'' +
                '}';
    }
}
