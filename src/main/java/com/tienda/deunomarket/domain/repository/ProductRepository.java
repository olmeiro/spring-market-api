package com.tienda.deunomarket.domain.repository;

import com.tienda.deunomarket.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    //indicamos el nombre de los métodos que queremos que cualquier repositorio que vaya a trabajar con productos tenga que implementar

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
