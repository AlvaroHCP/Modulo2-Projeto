package com.devinhouse.DEVinPharmacy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueResponse {
    private Integer nroRegistro;
    private String nome;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
