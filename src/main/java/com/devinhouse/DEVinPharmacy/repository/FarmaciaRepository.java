package com.devinhouse.DEVinPharmacy.repository;

import com.devinhouse.DEVinPharmacy.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
}
