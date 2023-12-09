package com.devinhouse.DEVinPharmacy.dto;

import com.devinhouse.DEVinPharmacy.data.TipoMedicamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoResponse {
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;
    @Enumerated(EnumType.STRING)
    private TipoMedicamento tipo;
}
