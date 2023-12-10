package com.devinhouse.DEVinPharmacy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstoqueTrocaRequest {
    @NotNull(message = "Campo Obrigatório!")
    private Long cnpjOrigem;
    @NotNull(message = "Campo Obrigatório!")
    private Long cnpjDestino;
    @NotNull(message = "Campo Obrigatório!")
    private Integer nroRegistro;
    @NotNull(message = "Campo Obrigatório!")
    private Integer quantidade;
}
