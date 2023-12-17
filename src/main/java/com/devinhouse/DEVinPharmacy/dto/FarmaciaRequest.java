package com.devinhouse.DEVinPharmacy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FarmaciaRequest {

    @NotNull(message = "Campo Obrigatório!")
    private Long cnpj;
    @NotEmpty(message = "Campo Obrigatório!")
    private String razaoSocial;
    @NotEmpty(message = "Campo Obrigatório!")
    private String nomeFantasia;
    @NotEmpty(message = "Campo Obrigatório!")
    private String email;
    private String telefone;
    @NotEmpty(message = "Campo Obrigatório!")
    private String celular;
    @Valid
    private EnderecoRequest endereco;
}
