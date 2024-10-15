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
    public CentroRecoleccion getCentroRecoleccionById(Long id) throws Exception{
        Optional<CentroRecoleccion> centroRecoleccionOptional = this.centroRecoleccionRepository.findById(id);
        if(centroRecoleccionOptional.isPresent()){
            return centroRecoleccionOptional.get();
        }else{
            throw new Exception("No se encontró el centro recoleccion con id:" + id);
        }
    }

    @Override
    public CentroRecoleccion updateCentroRecoleccion(Long id, String nombre) throws Exception{
        Optional<CentroRecoleccion> centroRecoleccionOptional = this.centroRecoleccionRepository.findById(id);
        if(centroRecoleccionOptional.isPresent()){
            CentroRecoleccion centroRecoleccion = centroRecoleccionOptional.get();
            centroRecoleccion.setNombre(nombre);
            try {
                return this.centroRecoleccionRepository.save(centroRecoleccion);
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }else{
            throw new Exception("No se encontró el centro recoleccion con id:" + id);
        }
    }

    @Override
    public void deleteCentroRecoleccion(Long id) throws Exception{
        Optional<CentroRecoleccion> centroRecoleccionOptional = this.centroRecoleccionRepository.findById(id);
        if(centroRecoleccionOptional.isPresent()){
            try {
                this.centroRecoleccionRepository.delete(centroRecoleccionOptional.get());
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }else{
            throw new Exception("No se encontró el centro recoleccion con id:" + id);
        }
    }
}
