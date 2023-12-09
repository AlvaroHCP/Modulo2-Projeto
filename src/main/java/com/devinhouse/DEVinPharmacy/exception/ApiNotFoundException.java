package com.devinhouse.DEVinPharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiNotFoundException {
    private String codigo;

    private String message;

    @Override
    public String toString() {
        return "{" +
                "item=" + codigo +
                ", message='" + message + '\'' +
                '}';
    }
}
