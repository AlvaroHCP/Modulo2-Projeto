package com.devinhouse.DEVinPharmacy.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueResponse {
    private Integer nroRegistro;
    private String nome;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
