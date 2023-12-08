package com.devinhouse.DEVinPharmacy.dto;

import com.devinhouse.DEVinPharmacy.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class FarmaciaResponse {
    private Long cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    private Endereco endereco;

    public FarmaciaResponse(Long cnpj, String razaoSocial, String nomeFantasia, String email, String telefone, String celular, Endereco endereco) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "AAAA{" +
                "cnpj=" + cnpj +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", celular='" + celular + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
