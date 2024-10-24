package com.dssd.BackendApi.repository;

import com.dssd.BackendApi.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findByTipo(String tipo);
}
