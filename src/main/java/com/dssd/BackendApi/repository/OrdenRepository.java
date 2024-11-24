package com.dssd.BackendApi.repository;

import com.dssd.BackendApi.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

    @Query("SELECT o FROM Orden o JOIN o.materialesOrden mo WHERE mo.material.id = :idMaterial AND o.fechaLimite BETWEEN :fechaComienzo AND :fechaFin AND o.centroRecoleccion IS NULL")
    Iterable<Orden> findOrdenByMateialIdBetweenDates(@Param("idMaterial") Long idMaterial, @Param("fechaComienzo") LocalDateTime fechaComienzo, @Param("fechaFin") LocalDateTime fechaFin);
}
