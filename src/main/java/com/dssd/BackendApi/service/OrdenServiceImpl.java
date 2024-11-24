package com.dssd.BackendApi.service;

import com.dssd.BackendApi.exception.*;
import com.dssd.BackendApi.model.CentroRecoleccion;
import com.dssd.BackendApi.model.Material;
import com.dssd.BackendApi.model.MaterialOrden;
import com.dssd.BackendApi.model.Orden;
import com.dssd.BackendApi.repository.OrdenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final MaterialService materialService;
    private final MaterialOrdenService materialOrdenService;
    private final CentroRecoleccionService centroRecoleccionService;

    public OrdenServiceImpl(
            OrdenRepository ordenRepository,
            MaterialService materialService,
            MaterialOrdenService materialOrdenService,
            CentroRecoleccionService centroRecoleccionService) {
        this.ordenRepository = ordenRepository;
        this.materialService = materialService;
        this.materialOrdenService = materialOrdenService;
        this.centroRecoleccionService = centroRecoleccionService;
    }

    @Override
    public Orden getOrdenById(Long id) throws RuntimeException {
        Optional<Orden> orden = this.ordenRepository.findById(id);
        if (orden.isPresent()) {
            return orden.get();
        } else {
            throw new OrdenNoEncontrada("No se encontro la orden con id: " + id);
        }
    }

    @Override
    public Orden createOrden(LocalDateTime fechaLimite, Map<Long, Float> materiales) {
        Orden orden = new Orden(fechaLimite);
        Orden ordenCreada = this.ordenRepository.save(orden);
        System.out.println("Orden id: " + ordenCreada.getId());
        List<MaterialOrden> materialesOrden = new ArrayList<>();
        materiales.forEach((id, cantidad) -> {
            System.out.println(id);
            System.out.println(cantidad);
            Optional<Material> material = this.materialService.getMaterialById(id);
            if (material.isPresent()) {
                MaterialOrden materialOrden = new MaterialOrden();
                materialOrden.setMaterial(material.get());
                materialOrden.setOrden(orden);
                materialOrden.setCantidad(cantidad);

                MaterialOrden.MaterialOrdenId materialOrdenId = new MaterialOrden.MaterialOrdenId(material.get().getId(), ordenCreada.getId());
                materialOrden.getId().setOrdenId(materialOrdenId.getOrdenId());
                materialOrden.getId().setMaterialId(materialOrdenId.getMaterialId());

                try {
                    MaterialOrden materialOrdenCreada = this.materialOrdenService.guardarMaterialOrden(materialOrden);
                    materialesOrden.add(materialOrdenCreada);
                } catch (Exception ignored) {
                }
            } else {
                throw new MaterialNoExiste("El material con id: " + id + " no existe");
            }
        });
        if (materialesOrden.size() == materiales.size()) {
            ordenCreada.setMaterialesOrden(materialesOrden);
            return this.ordenRepository.save(ordenCreada);
        } else {
            this.ordenRepository.delete(ordenCreada);
        }
        return null;
    }

    @Override
    public Iterable<Orden> getAllOrdenes() {
        return this.ordenRepository.findAll();
    }

    @Override
    public Iterable<Orden> getOrdenesByMaterialBetweenDates(String tipoMaterial, LocalDateTime fechaComienzo, LocalDateTime fechaFin) throws Exception {
        System.out.println("Busqueda por el tipo: " + tipoMaterial);
        Optional<Material> material = this.materialService.getMaterialByTipo(tipoMaterial);
        if (material.isPresent()) {
            return this.ordenRepository.findOrdenByMateialIdBetweenDates(material.get().getId(), fechaComienzo, fechaFin);
        }

        return List.of();
    }

    @Override
    public Iterable<Orden> getOrdenesByMaterialBetweenDates(Long idMaterial, LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
        System.out.println("Busqueda por el id: " + idMaterial);
        return this.ordenRepository.findOrdenByMateialIdBetweenDates(idMaterial, fechaComienzo, fechaFin);
    }

    @Override
    public Orden tomarOrden(Long id, Long idCentro) throws RuntimeException {
        Orden orden = this.getOrdenById(id);
        CentroRecoleccion centroRecoleccion = this.centroRecoleccionService.getCentroRecoleccionById(idCentro);

        List<MaterialOrden> materialesQueNoTrabaja = orden.getMaterialesOrden()
                .stream()
                .filter(materialOrden ->
                        !centroRecoleccion.getMateriales().contains(materialOrden.getMaterial()))
                .toList();

        if (materialesQueNoTrabaja.isEmpty()) {
            orden.setFechaInicio(LocalDateTime.now());
            orden.setCentroRecoleccion(centroRecoleccion);
            return this.ordenRepository.save(orden);
        } else {
            throw new NoTrabajaConMaterial("El centro de recoleccion con id: " + idCentro + " no trajaba con algunos de los materiales de la orden " + id, materialesQueNoTrabaja);
        }
    }

    @Override
    public Orden terminarOrden(Long id, Long idCentro) throws RuntimeException {
        Orden orden = this.getOrdenById(id);
        if (orden.getFechaLimite().isBefore(LocalDateTime.now())) {
            throw new FechaEntregaIncorrecta("No se puede terminar la orden, no se llego a la fecha limite");
        }
        orden.setFechaEntrega(LocalDateTime.now());
        return this.ordenRepository.save(orden);
    }

    @Override
    public void deleteOrden(Long id) throws Exception {

    }
}
