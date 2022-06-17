
package com.example.crudSpring.repository;

import com.example.crudSpring.entity.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository <Producto, Integer> {
    //busco por nombre
    Optional <Producto> findByNombre(String nombre);
    boolean existsByNombre (String nombre);
}
