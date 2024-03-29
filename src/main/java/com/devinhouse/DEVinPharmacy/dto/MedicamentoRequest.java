package com.devinhouse.DEVinPharmacy.dto;

import com.devinhouse.DEVinPharmacy.data.TipoMedicamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicamentoRequest {
    @NotNull(message = "Campo Obrigatório!")
    private Integer nroRegistro;
    @NotEmpty(message = "Campo Obrigatório!")
    private String nome;
    @NotEmpty(message = "Campo Obrigatório!")
    private String laboratorio;
    @NotEmpty(message = "Campo Obrigatório!")
    private String dosagem;
    private String descricao;
    @NotNull(message = "Campo Obrigatório!")
    private Float preco;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo Obrigatório!")
    private TipoMedicamento tipo;
}
