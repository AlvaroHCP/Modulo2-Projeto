package com.devinhouse.DEVinPharmacy.service;

import com.devinhouse.DEVinPharmacy.data.TipoMedicamento;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoInicializacaoService {

    public List<Medicamento> inicializarDados(){
        Medicamento medicamento1 = new Medicamento(
                1010,
                "Programapan",
                "Matrix",
                "2x ao dia",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend",
                111.00F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento2 = new Medicamento(
                7473,
                "Cafex",
                "Colombia Farm",
                "4x ao dia",
                "Descricao: Pellentesque non ultricies mauris, ut lobortis elit. Cras nec cursus nibh",
                51.50F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento3 = new Medicamento(
                2233,
                "Estomazol",
                "Acme",
                "1x ao dia",
                "Sed risus mauris, consectetur eget egestas vitae",
                22.50F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento4 = new Medicamento(
                8880,
                "Gelox",
                "Ice",
                "2x ao dia",
                "Quisque quam orci, vulputate sit amet",
                11.50F,
                TipoMedicamento.COMUM
        );

        Medicamento medicamento5 = new Medicamento(
                5656,
                "Aspirazol",
                "Acme",
                "3x ao dia",
                "Sed faucibus, libero iaculis pulvinar consequat, augue elit eleifend",
                10.50F,
                TipoMedicamento.CONTROLADO
        );

        Medicamento medicamento6 = new Medicamento(
                4040,
                "Propolizol",
                "Bee",
                "5x ao dia",
                "Nunc euismod ipsum purus, sit amet finibus libero ultricies in",
                10.50F,
                TipoMedicamento.CONTROLADO
        );

        return List.of(medicamento1,
                medicamento2,
                medicamento3,
                medicamento4,
                medicamento5,
                medicamento6
        );
    };
}
