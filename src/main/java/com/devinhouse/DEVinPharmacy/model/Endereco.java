package com.devinhouse.DEVinPharmacy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Embeddable
@Table(name="ENDERECO")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Column(nullable = false)
    private Long cep;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private Integer numero;
    private String complemento;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
}



