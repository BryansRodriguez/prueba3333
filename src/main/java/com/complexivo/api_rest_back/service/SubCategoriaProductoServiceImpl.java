/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.SubCategoriaProducto;
import com.complexivo.api_rest_back.repository.SubCategoriaProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SubCategoriaProductoServiceImpl implements SubCategoriaProductoService{

    @Autowired
    private SubCategoriaProductoRepository subcategoriaProductoRepository;

    @Override
    @Transactional
    public SubCategoriaProducto createSubCategoriaProducto(SubCategoriaProducto subcategoriaproducto) {
        return subcategoriaProductoRepository.save(subcategoriaproducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubCategoriaProducto> getAllSubCategoriaProducto() {
        return subcategoriaProductoRepository.findByListar();
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategoriaProducto getSubCategoriaProductoById(long subcatproid) {
        return subcategoriaProductoRepository.findById(subcatproid)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategoriaProducto", "id", subcatproid));

    }

    @Override
    @Transactional()
    public SubCategoriaProducto updateSubCategoriaProducto(long subcatproid, SubCategoriaProducto subcategoriaproducto) {
        SubCategoriaProducto scp = subcategoriaProductoRepository.findById(subcatproid)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategoriaProducto", "id", subcatproid));
        
        scp.setSubcatpronombre(subcategoriaproducto.getSubcatpronombre());
        scp.setSubcatprodescripcion(subcategoriaproducto.getSubcatprodescripcion());
        scp.setCatproid(subcategoriaproducto.getCatproid());

        return subcategoriaProductoRepository.save(scp);
    }

    @Override
    @Transactional
    public void deleteSubCategoriaProducto(long subcatproid) {
        SubCategoriaProducto categoriaproducto = subcategoriaProductoRepository.findById(subcatproid)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategoriaProducto", "id", subcatproid));

        subcategoriaProductoRepository.delete(categoriaproducto);
    }

    @Override
    public SubCategoriaProducto getSubCategoriaProductoByCatpronombre(String subcatpronombre) {
        SubCategoriaProducto categoriaproducto = subcategoriaProductoRepository.findBySubcatpronombre(subcatpronombre)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategoriaProducto", "subcatpronombre", subcatpronombre));

        return categoriaproducto;
    }
    
    @Override
    public List<SubCategoriaProducto> getAllCategorias(long id) {
        return subcategoriaProductoRepository.findByCatproid(id);
    }

}
