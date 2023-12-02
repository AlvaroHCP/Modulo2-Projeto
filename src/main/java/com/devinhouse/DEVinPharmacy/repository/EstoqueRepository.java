package com.devinhouse.DEVinPharmacy.repository;

import com.devinhouse.DEVinPharmacy.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
