package com.devinhouse.DEVinPharmacy.model;

import com.devinhouse.DEVinPharmacy.data.TipoMedicamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "MEDICAMENTOS")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @Column(nullable = false)
    private Integer nroRegistro;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String laboratorio;
    @Column(nullable = false)
    private String dosagem;
    private String descricao;
    @Column(nullable = false)
    private Float preco;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMedicamento tipo;
}

