
package com.complexivo.api_rest_back.service;

import com.complexivo.api_rest_back.exeption.AppException;
import com.complexivo.api_rest_back.exeption.ResourceNotFoundException;
import com.complexivo.api_rest_back.modelo.Producto;
import com.complexivo.api_rest_back.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto createProducto(Producto producto) {

        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getAllProducto() {
        return productoRepository.findByListar();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProductoById(long idproducto) {
        return productoRepository.findById(idproducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", idproducto));

    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProductoByProducto(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "producto", nombre));

        return producto;
    }

    @Override
    @Transactional()
    public Producto updateProducto(long idproducto, Producto producto) {
        Producto p = productoRepository.findById(idproducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", idproducto));

        p.setNombre(producto.getNombre());
        p.setPrecio(producto.getPrecio());
        p.setStock(producto.getStock());
        p.setNum_lote(producto.getNum_lote());
        p.setFecha_vencimiento(producto.getFecha_vencimiento());
        p.setDescripcion(producto.getDescripcion());
        p.setImagen(producto.getImagen());
        p.setCatproid(producto.getCatproid());
        p.setSubcatproid(producto.getSubcatproid());

        return productoRepository.save(p);
    }

    @Override
    @Transactional
    public void deleteProducto(long idproducto) {
        Producto producto = productoRepository.findById(idproducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", idproducto));

        productoRepository.delete(producto);
    }


    @Override
    public List<Producto> getAllCategorias(long id) {
        return productoRepository.findByCatproid(id);
    }

    @Override
    public List<Producto> getAllSubcategorias(long id) {
        return productoRepository.findBySubcatproid(id);
    }
    
      @Override
    public List<Object> getProductos(Long idempresa) {
        return productoRepository.obtenerIdempresaNombreCantidadPrecioByIdempresa(idempresa);
    }
    
    @Override
    public List<Producto> getIdempresa(long id) {
        return productoRepository.findByIdempresa(id);
    }
}
