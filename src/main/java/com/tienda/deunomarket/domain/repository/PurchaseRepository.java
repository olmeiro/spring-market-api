package com.tienda.deunomarket.domain.repository;

import com.tienda.deunomarket.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    // List<Purchase> getByClient(String clientId); Cómo a veces un cliente no tiene comprar mejor encerramos la lista en un Optional
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
