package com.devinhouse.DEVinPharmacy.model;

import com.devinhouse.DEVinPharmacy.data.TipoMedicamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEDICAMENTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;
    @Enumerated(EnumType.STRING)
    private TipoMedicamento tipo;
}

