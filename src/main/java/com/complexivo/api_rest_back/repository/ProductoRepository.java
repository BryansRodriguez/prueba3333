
package com.complexivo.api_rest_back.repository;


import com.complexivo.api_rest_back.modelo.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    public Optional<Producto> findByNombre(String nombre);
	
    public Boolean existsByNombre(String nombre);
    
    @Query(value = "select * from railway.producto where idproducto > 1 ",nativeQuery=true)
    public List<Producto> findByListar();
    
    @Query(value = "select p.idproducto, p.nombre, p.precio, p.stock, p.num_lote, p.fecha_vencimiento, p.descripcion, p.imagen,p.idempresa, p.catproid, p.subcatproid from railway.producto p inner join railway.categoriaproducto c on p.catproid = c.catproid where c.catproid = :id",nativeQuery=true)
    public List<Producto> findByCatproid(long id);
    
    @Query(value = "select p.idproducto, p.nombre, p.precio, p.stock, p.num_lote, p.fecha_vencimiento, p.descripcion, p.imagen,p.idempresa, p.catproid, p.subcatproid from railway.producto p inner join railway.subcategoriaproducto c on p.subcatproid = c.subcatproid where c.subcatproid = :id",nativeQuery=true)
    public List<Producto> findBySubcatproid(long id);
    
    @Query(value = "select e.idempresa as 'idempresa', e.empnombre as 'nombre', p.nombre, sum(c.cantidad) as 'cantidad', (p.precio*sum(c.cantidad)) as 'venta' \n" +
                    "from empresa e inner join producto p on e.idempresa = p.idempresa inner join detalle c on p.idproducto = c.idproducto \n" +
                    "where e.idempresa = :idempresa \n" +
                    "group by p.idproducto order by sum(c.cantidad) desc",nativeQuery=true)
    public List<Object> obtenerIdempresaNombreCantidadPrecioByIdempresa(Long idempresa);
    
    @Query(value = "select p.idproducto, p.nombre, p.precio, p.stock, p.num_lote, p.fecha_vencimiento, p.descripcion, p.imagen,p.idempresa, p.catproid, p.subcatproid from railway.producto p inner join railway.empresa c on p.idempresa = c.idempresa where c.idempresa = :id",nativeQuery=true)
    public List<Producto> findByIdempresa(long id);
}
