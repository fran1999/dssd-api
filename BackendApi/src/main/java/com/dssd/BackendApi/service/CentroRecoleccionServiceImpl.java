package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.CentroRecoleccion;
import com.dssd.BackendApi.repository.CentroRecoleccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CentroRecoleccionServiceImpl implements CentroRecoleccionService{

    @Autowired
    private CentroRecoleccionRepository centroRecoleccionRepository;

    @Override
    public CentroRecoleccion createCentroRecoleccion(String nombre) {
        CentroRecoleccion centroRecoleccion = new CentroRecoleccion();
        centroRecoleccion.setNombre(nombre);
        try {
            return this.centroRecoleccionRepository.save(centroRecoleccion);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Iterable<CentroRecoleccion> getAllCentrosRecoleccion() {
        try{
            return this.centroRecoleccionRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<CentroRecoleccion> getCentroRecoleccionById(Long id) {
        try{
            return this.centroRecoleccionRepository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CentroRecoleccion updateCentroRecoleccion(Long id, String nombre) {
        Optional<CentroRecoleccion> centroRecoleccionOptional = this.centroRecoleccionRepository.findById(id);
        if(centroRecoleccionOptional.isPresent()){
            CentroRecoleccion centroRecoleccion = centroRecoleccionOptional.get();
            centroRecoleccion.setNombre(nombre);
            try {
                return this.centroRecoleccionRepository.save(centroRecoleccion);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public void deleteCentroRecoleccion(Long id) {
        Optional<CentroRecoleccion> centroRecoleccionOptional = this.centroRecoleccionRepository.findById(id);
        if(centroRecoleccionOptional.isPresent()){
            try {
                this.centroRecoleccionRepository.delete(centroRecoleccionOptional.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
