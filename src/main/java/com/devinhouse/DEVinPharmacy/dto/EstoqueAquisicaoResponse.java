package com.devinhouse.DEVinPharmacy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueAquisicaoResponse {
    private Long cnpj;
    private Integer nroRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
