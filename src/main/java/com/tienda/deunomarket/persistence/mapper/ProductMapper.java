package com.tienda.deunomarket.persistence.mapper;

import com.tienda.deunomarket.domain.Product;
import com.tienda.deunomarket.persistence.entities.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
            //como ya tenemos el mappe de category lo incluimos en la anotación del mapper.
    })
    Product toProduct(Producto product);
    List<Product> toProducts(List<Producto> productos);

    //Conversion externa:
    //Ignoramos codigo de barras:
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
