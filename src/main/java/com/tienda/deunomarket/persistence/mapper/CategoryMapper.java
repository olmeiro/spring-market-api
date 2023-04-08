package com.tienda.deunomarket.persistence.mapper;

import com.tienda.deunomarket.domain.Category;
import com.tienda.deunomarket.persistence.entities.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //diseñamos los mappers:

    @Mappings( //como traducir los objetos ->
            {
                    //el idCategoria de Categoria(entitie) lo llevo a categoryId de Category(domain).
                    @Mapping(source = "idCategoria", target = "categoryId") ,
                    @Mapping(source = "estado", target = "category") ,
                    @Mapping(source = "descripcion", target = "active") ,
            }
    )
    Category toCategory(Category categoria); //Convertimos una categoria en Category

    //Conversion externa
    //mapeo inverso al anterior sin necesidad de hacerlo de nuevo.
    //ya que en Category no tenemos productos lo ignoramos en el mapeo.
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}
