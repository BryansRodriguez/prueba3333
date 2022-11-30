/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.complexivo.api_rest_back.repository;

import com.complexivo.api_rest_back.modelo.Empresa;
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
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    public Optional<Empresa> findByEmpnombre(String empnombre);
	
    public Boolean existsByEmpruc(String empruc);
    
    @Query(value = "select * from railway.empresa where idempresa > 1 ",nativeQuery=true)
    public List<Empresa> findByListar();
    
    
    @Query(value = "select e.empnombre, p.nombre, sum(c.cantidad), sum(c.precio) from railway.empresa e inner join railway.producto p on e.idempresa = p.idempresa inner join railway.detalle c on p.idproducto = c.idproducto where p.idempresa = :id",nativeQuery=true)
    public List<Empresa> findByidpro(long id);
    
    @Query(value = "Select * from railway.empresa u where u.usu_id = :usu_id",nativeQuery=true)
    public Empresa findByUser(long usu_id);
}
