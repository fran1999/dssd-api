package com.dssd.BackendApi.repository;

import com.dssd.BackendApi.model.MaterialOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialOrdenRepository extends JpaRepository<MaterialOrden, Long> {
}
