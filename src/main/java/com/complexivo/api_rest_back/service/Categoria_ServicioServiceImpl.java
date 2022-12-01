/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.Categoria_Servicio;
import com.complexivo.api_rest_back.modelo.Servicio;
import com.complexivo.api_rest_back.repository.Categoria_ServicioRepository;
import com.complexivo.api_rest_back.repository.ServicioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class Categoria_ServicioServiceImpl implements Categoria_ServicioService{

    @Autowired
    private Categoria_ServicioRepository repository;
    
    @Autowired ServicioRepository servi;
    
    @Override
    public Categoria_Servicio createCategoria(Categoria_Servicio categoria_Servicio) {
        if (repository.existsByCatnombre(categoria_Servicio.getCatnombre())) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "El nombre se categoria ingresada ya se encuentra en otro registro");
        }

        return repository.save(categoria_Servicio);
    }

    @Override
    public List<Categoria_Servicio> getAllCategoria() {
         return repository.findByListar();
    }

    @Override
    public Categoria_Servicio getCategoriadById(long idcatser) {
        return repository.findById(idcatser)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria_Servicio", "id", idcatser));

    }

    @Override
    public Categoria_Servicio getCategoriaByNombre(String catnombre) {
        Categoria_Servicio cat= repository.findByCatnombre(catnombre)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria_Servicio", "catnombre", catnombre));

        return cat; 
    }

    @Override
    public Categoria_Servicio updateCategoria(long idcatser, Categoria_Servicio categoria_Servicio) {
        Categoria_Servicio c = repository.findById(idcatser)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria_Servicio", "id", idcatser));

        c.setCatnombre(categoria_Servicio.getCatnombre());
        c.setCatdetalle(categoria_Servicio.getCatdetalle());
        return repository.save(c);
    }

    @Override
    public void deleteCategoria(long idcatser) {
         Categoria_Servicio cat = repository.findById(idcatser)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria_Servicio", "id", idcatser));

        repository.delete(cat);
    }

  
   
    
}
