/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.complexivo.api_rest_back.repository;

import com.complexivo.api_rest_back.modelo.Subcategoria_Servicio;
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
public interface Subcategoria_ServicioRepository extends JpaRepository<Subcategoria_Servicio, Long>{
    public Optional<Subcategoria_Servicio> findBySubcatnombre(String subcatnombre);
	
    public Boolean existsBySubcatnombre(String subcatnombre);
    
    @Query(value = "select * from railway.subcategoria_servicio where idsubcat > 1 ",nativeQuery=true)
    public List<Subcategoria_Servicio> findByListar();
    
    @Query(value = "select s.idsubcat, s.subcatnombre, s.subcatdetalle, s.idcatser from railway.subcategoria_servicio s inner join railway.categoria_servicio c on s.idcatser = c.idcatser where c.idcatser = :id",nativeQuery=true)
    public List<Subcategoria_Servicio> findByidcatser(long id);
}
