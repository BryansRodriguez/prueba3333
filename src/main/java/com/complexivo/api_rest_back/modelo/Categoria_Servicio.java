/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.complexivo.api_rest_back.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Usuario
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria_servicio", uniqueConstraints = { @UniqueConstraint(columnNames = { "idcatser" }) })
public class Categoria_Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcatser;
    
    @NotEmpty(message = "Nombre de Categoria no ingresado")
    @Size(min = 2, max = 70, message = "El nombre debe tener mas de 2 caracteres")
    private String catnombre;
    
    @NotEmpty(message = "Detalle Servicio no ingresado")
    @Size(min = 2, max = 70, message = "El detalle Servicio debe tener mas de 2 caracteres")
    private String catdetalle;
    
    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="idcatser")
    private List<Servicio> servicios;
    
    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="catser_id")
    private List<Subcategoria_Servicio> subcategoria_Servicio;
    
}
