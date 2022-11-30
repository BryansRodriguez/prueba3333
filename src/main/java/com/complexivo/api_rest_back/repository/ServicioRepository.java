/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.complexivo.api_rest_back.repository;

import com.complexivo.api_rest_back.modelo.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Usuario
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    public Optional<Servicio> findBySernombre(String sernombre);
	
    public Boolean existsBySernombre(String sernombre);
    
    @Query(value = "select * from railway.servicio where idservicio > 1 ",nativeQuery=true)
    public List<Servicio> findByListar();
    
    @Query(value = "select s.idservicio, s.sernombre, s.serprecio, s.serdescripcion, s.serduracion, s.serimagen,s.idempresa, s.idcatser, s.idsubcat from railway.servicio s inner join railway.categoria_servicio c on s.idcatser = c.idcatser where c.idcatser = :id",nativeQuery=true)
    public List<Servicio> findByidcatser(long id);
    
    @Query(value = "select s.idservicio, s.sernombre, s.serprecio, s.serdescripcion, s.serduracion, s.serimagen,s.idempresa, s.idcatser, s.idsubcat from railway.servicio s inner join railway.subcategoria_servicio c on s.idsubcat = c.idsubcat where c.idsubcat = :id",nativeQuery=true)
    public List<Servicio> findByidsubcatser(long id);
    
    @Query(value = "select s.idservicio, s.sernombre, s.serprecio, s.serdescripcion, s.serduracion, s.serimagen,s.idempresa, s.idcatser, s.idsubcat from railway.servicio s inner join railway.empresa c on s.idempresa = c.idempresa where c.idempresa = :id",nativeQuery=true)
    public List<Servicio> findByIdempresa(long id);
}
