package com.tienda.deunomarket.domain.service;

import com.tienda.deunomarket.domain.Product;
import com.tienda.deunomarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //creamos los métodos según la Interfaz ProductRepository
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    //forma 1 para delete
    // public boolean delete(int productId){
    //     return getProduct(productId).map(product -> {
    //         productRepository.delete(productId);
    //         return true;
    //     }).orElse(false);
    // }

    //forma 2
    public boolean delete(int productId){
        if (getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return  true;
        }else{
            return false;
        }
    }
}
