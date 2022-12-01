/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.Subcategoria_Servicio;
import com.complexivo.api_rest_back.repository.Subcategoria_ServicioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class Subcategoria_ServicioServiceImpl implements Subategoria_ServicioService{

    @Autowired
    private Subcategoria_ServicioRepository repository;
    
    @Override
    public Subcategoria_Servicio createSubcategoria(Subcategoria_Servicio subcategoria_Servicio) {
        if (repository.existsBySubcatnombre(subcategoria_Servicio.getSubcatnombre())) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "El nombre se la subcategoria ingresada ya se encuentra en otro registro");
        }

        return repository.save(subcategoria_Servicio);
    }

    @Override
    public List<Subcategoria_Servicio> getAllSubcategoria() {
        return repository.findByListar();
    }

    @Override
    public Subcategoria_Servicio getSubcategoriadById(long idsubcatser) {
        return repository.findById(idsubcatser)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategoria_Empresa", "id", idsubcatser));

    }

    @Override
    public Subcategoria_Servicio getSubcategoriaByNombre(String subcatnombre) {
        Subcategoria_Servicio cat= repository.findBySubcatnombre(subcatnombre)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategoria_Servicio", "subcatnombre", subcatnombre));

        return cat; 
    }

    @Override
    public Subcategoria_Servicio updateSubcategoria(long idsubcatser, Subcategoria_Servicio subcategoria_Servicio) {
        Subcategoria_Servicio c = repository.findById(idsubcatser)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategoria_Servicio", "id", idsubcatser));

        c.setSubcatnombre(subcategoria_Servicio.getSubcatnombre());
        c.setSubcatdetalle(subcategoria_Servicio.getSubcatdetalle());
        c.setIdcatser(subcategoria_Servicio.getIdcatser());
        
        return repository.save(c);
    }

    @Override
    public void deleteSubcategoria(long idsubcatser) {
         Subcategoria_Servicio cat = repository.findById(idsubcatser)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategoria_Servicio", "id", idsubcatser));

        repository.delete(cat);
    }
    
    @Override
    public List<Subcategoria_Servicio> getAllCategorias(long id) {
        return repository.findByidcatser(id);
    }
    
}
