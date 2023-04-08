package com.tienda.deunomarket.persistence;

import com.tienda.deunomarket.persistence.crud.ProductoCrudRepository;
import com.tienda.deunomarket.persistence.entities.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //esta clase se encarga de interactuar con la BBDD mejor que @Component
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    //recuperar todos los productos:
    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByAsc(idCategoria);
    }

    public Optional<List<Producto>>  getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    //Producto en particular por id
    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    //guardar producto:
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    //eliminar producto:
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
