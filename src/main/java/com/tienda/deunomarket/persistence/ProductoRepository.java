package com.tienda.deunomarket.persistence;

import com.tienda.deunomarket.persistence.crud.ProductoCrudRepository;
import com.tienda.deunomarket.persistence.entities.Producto;

import java.util.List;
import java.util.Optional;

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
}
