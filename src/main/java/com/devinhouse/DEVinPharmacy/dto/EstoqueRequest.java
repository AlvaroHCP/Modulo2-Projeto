package com.devinhouse.DEVinPharmacy.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstoqueRequest {
    @NotNull(message = "Campo Obrigatório!")
    private Long cnpj;
    @NotNull(message = "Campo Obrigatório!")
    private Integer nroRegistro;
    @NotNull(message = "Campo Obrigatório!")
    private Integer quantidade;
}
