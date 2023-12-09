package com.devinhouse.DEVinPharmacy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoRequest {
    @NotNull(message = "Campo Obrigatório!")
    private Long cep;
    @NotNull(message = "Campo Obrigatório!")
    private String logradouro;
    @NotNull(message = "Campo Obrigatório!")
    private Integer numero;
    @NotNull(message = "Campo Obrigatório!")
    private String bairro;
    @NotNull(message = "Campo Obrigatório!")
    private String cidade;
    @NotNull(message = "Campo Obrigatório!")
    private String estado;
    private String complemento;
    @NotNull(message = "Campo Obrigatório!")
    private Double latitude;
    @NotNull(message = "Campo Obrigatório!")
    private Double longitude;
}
