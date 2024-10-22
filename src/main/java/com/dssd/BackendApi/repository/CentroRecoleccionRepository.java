package com.dssd.BackendApi.repository;

import com.dssd.BackendApi.model.CentroRecoleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRecoleccionRepository extends JpaRepository<CentroRecoleccion, Long> {
}
