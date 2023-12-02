package com.devinhouse.DEVinPharmacy.repository;

import com.devinhouse.DEVinPharmacy.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
}
