/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.Empresa;
import com.complexivo.api_rest_back.repository.EmpresaRepository;
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
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public Empresa createEmpresa(Empresa empresa) {
        if (empresaRepository.existsByEmpruc(empresa.getEmpruc())) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "El ruc ingresado ya se encuentra en otro registro");
        }

        return empresaRepository.save(empresa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> getAllEmpresa() {
        return empresaRepository.findByListar();
    }

    @Override
    @Transactional(readOnly = true)
    public Empresa getEmpresaById(long idempresa) {
        return empresaRepository.findById(idempresa)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", "id", idempresa));

    }

    @Override
    @Transactional(readOnly = true)
    public Empresa getEmpresaByEmpnombre(String empnombre) {
        Empresa empre = empresaRepository.findByEmpnombre(empnombre)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", "empnombre", empnombre));

        return empre;
    }

    @Override
    @Transactional
    public Empresa updateEmpresa(long idempresa, Empresa empresa) {
        Empresa e = empresaRepository.findById(idempresa)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", "id", idempresa));

        e.setEmpruc(empresa.getEmpruc());
        e.setEmpnombre(empresa.getEmpnombre());
        e.setEmprepresentante(empresa.getEmprepresentante());
        e.setEmprazon(empresa.getEmprazon());
        e.setEmptelefono(empresa.getEmptelefono());
        e.setEmpcorreo(empresa.getEmpcorreo());
        e.setEmpingresoanual(empresa.getEmpingresoanual());
        e.setEmpnumtrabajadores(empresa.getEmpnumtrabajadores());
        e.setEmpciudad(empresa.getEmpciudad());
        e.setEmpdireccion(empresa.getEmpdireccion());
        e.setEmpobservaciones(empresa.getEmpobservaciones());
        e.setEmpfecha(empresa.getEmpfecha());
        e.setEmpestado(empresa.getEmpestado());
        //e.setEmpcategorias(empresa.getEmpcategorias());
        //e.setEmpsubcategoria(empresa.getEmpsubcategoria());


        return empresaRepository.save(e);
    }

    @Override
    @Transactional
    public void deleteEmpresa(long idempresa) {
        Empresa empre = empresaRepository.findById(idempresa)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", "id", idempresa));

        empresaRepository.delete(empre);
    }

    @Override
    public List<Empresa> getAllReporte(long id) {
        return empresaRepository.findByidpro(id);
    }

    @Override
    public Empresa getfindByUser(long usu_id) {
       return empresaRepository.findByUser(usu_id); 
    }
  

}
