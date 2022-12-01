/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.complexivo.api_rest_back.repository;

import com.complexivo.api_rest_back.modelo.Categoria_Servicio;
import com.complexivo.api_rest_back.modelo.Servicio;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface Categoria_ServicioRepository extends JpaRepository<Categoria_Servicio, Long>{
    public Optional<Categoria_Servicio> findByCatnombre(String catnombre);
	
    public Boolean existsByCatnombre(String catnombre);
    
    @Query(value = "select * from railway.categoria_servicio where idcatser > 1 ",nativeQuery=true)
    public List<Categoria_Servicio> findByListar();
    
    
}


