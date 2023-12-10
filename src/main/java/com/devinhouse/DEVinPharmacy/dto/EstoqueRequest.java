package com.devinhouse.DEVinPharmacy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstoqueRequest {
    @NotNull(message = "Campo Obrigatóorio!")
    private Long cnpj;
    @NotNull(message = "Campo Obrigatóorio!")
    private Integer nroRegistro;
    @NotNull(message = "Campo Obrigatóorio!")
    private Integer quantidade;
}
