/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.complexivo.api_rest_back.repository;

import com.complexivo.api_rest_back.modelo.Categoria_Empresa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface Categoria_EmpresaRepository extends JpaRepository<Categoria_Empresa, Long>{
    public Optional<Categoria_Empresa> findByCatnombre(String catnombre);
	
    public Boolean existsByCatnombre(String catnombre);
}
