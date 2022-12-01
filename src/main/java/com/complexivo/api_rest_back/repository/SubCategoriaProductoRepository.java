
package com.complexivo.api_rest_back.repository;


import com.complexivo.api_rest_back.modelo.SubCategoriaProducto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SubCategoriaProductoRepository extends JpaRepository<SubCategoriaProducto, Long> {
    
    public Optional<SubCategoriaProducto> findBySubcatpronombre(String subcatpronombre);
	
    public Boolean existsBySubcatpronombre(String subcatpronombre);
    
    @Query(value = "select * from railway.subcategoriaproducto where subcatproid > 1 ",nativeQuery=true)
    public List<SubCategoriaProducto> findByListar();
    
    @Query(value = "select p.subcatproid, p.subcatpronombre, p.subcatprodescripcion, p.catproid from railway.subcategoriaproducto p inner join railway.categoriaproducto c on p.catproid = c.catproid where c.catproid = :id",nativeQuery=true)
    public List<SubCategoriaProducto> findByCatproid(long id);
}
