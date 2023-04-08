package com.tienda.deunomarket.persistence.mapper;

import com.tienda.deunomarket.domain.PurchaseItem;
import com.tienda.deunomarket.persistence.entities.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"), //por ser pk compuesta id.idProducto
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"), //como source y target son iguales podemos eliminar esta linea
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true),
    })
    ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
