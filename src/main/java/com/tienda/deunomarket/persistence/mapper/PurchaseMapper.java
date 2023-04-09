package com.tienda.deunomarket.persistence.mapper;

import com.tienda.deunomarket.domain.Purchase;
import com.tienda.deunomarket.persistence.entities.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items") //de PurchaseItemMapper
    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras); //toma el mapeo del singular o el que está arriba

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
