/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.Subcategoria_Empresa;
import com.complexivo.api_rest_back.repository.Subcategoria_EmpresaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class Subcategoria_EmpresaServiceImpl implements Subategoria_EmpresaService{

    @Autowired
    private Subcategoria_EmpresaRepository repository;
    
    @Override
    public Subcategoria_Empresa createSubcategoria(Subcategoria_Empresa subcategoria_Empresa) {
        if (repository.existsBySubcatnombre(subcategoria_Empresa.getSubcatnombre())) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "El nombre se la subcategoria ingresada ya se encuentra en otro registro");
        }

        return repository.save(subcategoria_Empresa);
    }

    @Override
    public List<Subcategoria_Empresa> getAllSubcategoria() {
         return repository.findAll();
    }

    @Override
    public Subcategoria_Empresa getSubcategoriadById(long idsubcatemp) {
        return repository.findById(idsubcatemp)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategoria_Empresa", "id", idsubcatemp));

    }

    @Override
    public Subcategoria_Empresa getSubcategoriaByNombre(String subcatnombre) {
        Subcategoria_Empresa cat= repository.findBySubcatnombre(subcatnombre)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria_Empresa", "subcatnombre", subcatnombre));

        return cat; 
    }

    @Override
    public Subcategoria_Empresa updateSubcategoria(long idsubcatemp, Subcategoria_Empresa subcategoria_Empresa) {
        Subcategoria_Empresa c = repository.findById(idsubcatemp)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategoria_Empresa", "id", idsubcatemp));

        c.setSubcatnombre(subcategoria_Empresa.getSubcatnombre());
        c.setSubcatdescripcion(subcategoria_Empresa.getSubcatdescripcion());
        c.setIdcatemp(subcategoria_Empresa.getIdcatemp());

        return repository.save(c);
    }

    @Override
    public void deleteSubcategoria(long idsubcatemp) {
         Subcategoria_Empresa cat = repository.findById(idsubcatemp)
                .orElseThrow(() -> new ResourceNotFoundException("Subcategoria_Empresa", "id", idsubcatemp));

        repository.delete(cat);
    }
    @Override
    public List<Subcategoria_Empresa> getAllCategorias(long id) {
        return repository.findByIdcatemp(id);
    }
}
