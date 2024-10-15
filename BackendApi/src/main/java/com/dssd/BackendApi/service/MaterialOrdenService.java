package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.MaterialOrden;
import com.dssd.BackendApi.repository.MaterialOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialOrdenService {

    @Autowired
    private MaterialOrdenRepository materialOrdenRepository;

    public MaterialOrden guardarMaterialOrden(MaterialOrden materialOrden) throws Exception{
        try{
            return materialOrdenRepository.save(materialOrden);
        }catch (Exception e){
            throw new Exception("Error al guardar material_orden");
        }
    }
}
