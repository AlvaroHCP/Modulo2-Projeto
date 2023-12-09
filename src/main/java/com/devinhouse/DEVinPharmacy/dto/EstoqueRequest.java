package com.devinhouse.DEVinPharmacy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstoqueRequest {
    @NotNull(message = "Campo Obrigatóorio!")
    private Long cnpj;
    @NotNull(message = "Campo Obrigatóorio!")
    private Integer nroRegistro;
    @NotNull(message = "Campo Obrigatóorio!")
    private Integer quantidade;
}
