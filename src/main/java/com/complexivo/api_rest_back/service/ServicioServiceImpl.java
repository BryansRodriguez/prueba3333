/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.Servicio;
import com.complexivo.api_rest_back.repository.ServicioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Usuario
 */
@Service
public class ServicioServiceImpl implements ServicioService{

    @Autowired
    private ServicioRepository Repository;
    
    @Override
    @Transactional
    public Servicio createServicio(Servicio servicio) {
        if (Repository.existsBySernombre(servicio.getSernombre())) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "El Nombre de Servicio ingresado ya se encuentra en otro registro");
        }

        return Repository.save(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> getAllServicio() {
        return Repository.findByListar();
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio getServicioById(long idservicio) {
         return Repository.findById(idservicio)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", idservicio));

    }

    @Override
    @Transactional(readOnly = true)
    public Servicio getServicioBySernombre(String sernombre) {
        Servicio servicio = Repository.findBySernombre(sernombre)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "sernombre", sernombre));

        return servicio;
    }

    @Override
    @Transactional
    public Servicio updateServicio(long idservicio, Servicio servicio) {
        Servicio e = Repository.findById(idservicio)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", idservicio));

        e.setSernombre(servicio.getSernombre());
        e.setSerprecio(servicio.getSerprecio());
        e.setSerdescripcion(servicio.getSerdescripcion());
        e.setSerduracion(servicio.getSerduracion());
        e.setSerimagen(servicio.getSerimagen());
        e.setIdcatser(servicio.getIdcatser());
        e.setIdsubcat(servicio.getIdsubcat());


        return Repository.save(e);
    }

    @Override
    @Transactional
    public void deleteServicio(long idservicio) {
        Servicio ser = Repository.findById(idservicio)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", idservicio));

        Repository.delete(ser);
    }

    @Override
    public List<Servicio> getAllCategorias(long id) {
        return Repository.findByidcatser(id);
    }

    @Override
    public List<Servicio> getAllSubcategorias(long id) {
        return Repository.findByidsubcatser(id);
    }
    
  @Override
    public List<Servicio> getIdempresa(long id) {
        return Repository.findByIdempresa(id);
    }
    

    
}
