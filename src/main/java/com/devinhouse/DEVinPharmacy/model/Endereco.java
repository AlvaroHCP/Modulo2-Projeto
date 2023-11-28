package com.devinhouse.DEVinPharmacy.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Embeddable
public class Endereco {
    private Long cep;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private Double latitude;
    private Double longitude;
}
