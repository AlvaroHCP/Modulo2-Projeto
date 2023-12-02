package com.devinhouse.DEVinPharmacy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="FARMACIAS")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Farmacia {
    @Id
    @Column(nullable = false)
    private Long cnpj;
    @Column(nullable = false)
    private String razaoSocial;
    @Column(nullable = false)
    private String nomeFantasia;
    @Column(nullable = false)
    private String email;
    private String telefone;
    @Column(nullable = false)
    private String celular;
    @Embedded
    private Endereco endereco;
}
