package com.devinhouse.DEVinPharmacy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUES")
@IdClass(IdEstoque.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {
    @Id
    private Long cnpj;
    @Id
    private Integer nroRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
