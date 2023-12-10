package com.devinhouse.DEVinPharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueAlteracaoResponse {
    private Long cnpj;
    private Integer nroRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
