package com.tienda.deunomarket.persistence;

import com.tienda.deunomarket.domain.Product;
import com.tienda.deunomarket.domain.repository.ProductRepository;
import com.tienda.deunomarket.persistence.crud.ProductoCrudRepository;
import com.tienda.deunomarket.persistence.entities.Producto;
import com.tienda.deunomarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

//    public List<Producto> getAll() {
    @Override
    public List<Product> getAll() {
        //return (List<Producto>) productoCrudRepository.findAll();
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods)); //retorna Optional
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        //creamos el producto
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }


    //eliminar producto:
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

    //actualizar producto:
    @Override
    public Product update(Product product) {

        return getProduct(product.getProductId()).map(productToUpdate -> {
            productToUpdate.setName(product.getName());
            productToUpdate.setCategoryId(product.getCategoryId());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStock(product.getStock());
            productToUpdate.setActive(product.isActive());
            Producto producto = mapper.toProducto(productToUpdate);
            return save(mapper.toProduct(producto));
        }).orElse(null);

    }
}
