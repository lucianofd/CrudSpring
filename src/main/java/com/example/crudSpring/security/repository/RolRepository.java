
package com.example.crudSpring.security.repository;

import com.example.crudSpring.security.entity.Rol;
import com.example.crudSpring.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    
    
}
