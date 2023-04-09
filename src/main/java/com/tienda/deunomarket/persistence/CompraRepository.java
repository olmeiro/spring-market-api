package com.tienda.deunomarket.persistence;

import com.tienda.deunomarket.domain.Purchase;
import com.tienda.deunomarket.domain.repository.PurchaseRepository;
import com.tienda.deunomarket.persistence.crud.CompraCrudRepository;
import com.tienda.deunomarket.persistence.entities.Compra;
import com.tienda.deunomarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);

        //guardar en cascada: compras conocen los productos y a que compra pertenecen
        //compra.getProductos().forEach(product -> product.setCompra(compra)); //a que compra pertenece

        if (compra.getProductos() != null) {
            compra.getProductos().forEach(product -> product.setCompra(compra));
        }


        return mapper.toPurchase((compraCrudRepository.save(compra)));
    }
}
