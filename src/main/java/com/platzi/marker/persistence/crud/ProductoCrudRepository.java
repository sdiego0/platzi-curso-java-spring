package com.platzi.marker.persistence.crud;

import com.platzi.marker.persistence.entity.Producto;
/**import org.springframework.data.jpa.repository.Query;**/
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {

    /**@Query(value = "Select * From productos where id_categoria =?",nativeQuery = true)**/
    List<Producto>findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>>findByCantidadStockLessThanAndEstado(int cantidadStock,Boolean estado);
}
