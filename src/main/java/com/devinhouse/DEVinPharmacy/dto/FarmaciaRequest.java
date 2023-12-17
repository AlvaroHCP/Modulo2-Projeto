package com.devinhouse.DEVinPharmacy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FarmaciaRequest {

    @NotNull(message = "Campo Obrigatório!")
    @NotEmpty(message = "Campo Obrigatório!")
    private Long cnpj;
    @NotNull(message = "Campo Obrigatório!")
    @NotEmpty(message = "Campo Obrigatório!")
    private String razaoSocial;
    @NotNull(message = "Campo Obrigatório!")
    @NotEmpty(message = "Campo Obrigatório!")
    private String nomeFantasia;
    @NotNull(message = "Campo Obrigatório!")
    @NotEmpty(message = "Campo Obrigatório!")
    private String email;
    private String telefone;
    @NotNull(message = "Campo Obrigatório!")
    @NotEmpty(message = "Campo Obrigatório!")
    private String celular;
    @Valid
    @NotNull(message = "Campo Obrigatório!")
    @NotEmpty(message = "Campo Obrigatório!")
    private EnderecoRequest endereco;
}
