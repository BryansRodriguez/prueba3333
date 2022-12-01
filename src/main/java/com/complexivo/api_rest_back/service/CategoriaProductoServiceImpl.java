/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.CategoriaProducto;
import com.complexivo.api_rest_back.repository.CategoriaProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService{

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional
    public CategoriaProducto createCategoriaProducto(CategoriaProducto categoriaproducto) {
        return categoriaProductoRepository.save(categoriaproducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaProducto> getAllCategoriaProducto() {
        return categoriaProductoRepository.findByListar();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaProducto getCategoriaProductoById(long catproid) {
        return categoriaProductoRepository.findById(catproid)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", "id", catproid));

    }

    @Override
    @Transactional()
    public CategoriaProducto updateCategoriaProducto(long catproid, CategoriaProducto categoriaproducto) {
        CategoriaProducto cp = categoriaProductoRepository.findById(catproid)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", "id", catproid));
        
        cp.setCatpronombre(categoriaproducto.getCatpronombre());
        cp.setCatprodescripcion(categoriaproducto.getCatprodescripcion());

        return categoriaProductoRepository.save(cp);
    }

    @Override
    @Transactional
    public void deleteCategoriaProducto(long catproid) {
        CategoriaProducto categoriaproducto = categoriaProductoRepository.findById(catproid)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", "id", catproid));

        categoriaProductoRepository.delete(categoriaproducto);
    }

    @Override
    public CategoriaProducto getCategoriaProductoByCatpronombre(String catpronombre) {
        CategoriaProducto categoriaproducto = categoriaProductoRepository.findByCatpronombre(catpronombre)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", "catpronombre", catpronombre));

        return categoriaproducto;
    }

}
