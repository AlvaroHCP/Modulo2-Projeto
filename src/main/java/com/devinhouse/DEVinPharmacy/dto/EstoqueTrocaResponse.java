package com.devinhouse.DEVinPharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueTrocaResponse {
    private Integer nroRegistro;
    private Long cnpjOrigem;
    private Integer quantidadeOrigem;
    private Long cnpjDestino;
    private Integer quantidadeDestino;
}
