package com.tienda.deunomarket.persistence.crud;

import com.tienda.deunomarket.persistence.entities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Recuperar toda la lista de productos que pertenezcan a una categoria especíica ordenados de manera ascendente:

    // De manera nativa:
    //  @Query(value= "SELECT * from productos WHERE id_categoria = ?", nativeQuery = true) //si tenemos el query no hay necesidad de llamarlo findByIdCategoria:
    //  List<Producto> getByCategoria(int idCategoria);

    //  Con Query methods: sin consultas SQL ->
    List<Producto> findByIdCategoriaOrderByAsc(int idCategoria);

    //Operadores opcionales de Query methods:
    //Productos escazos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);



}
