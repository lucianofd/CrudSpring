
package com.example.crudSpring.security.repository;

import com.example.crudSpring.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
 
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existByNombreUsuario(String nombreUsuario);
    boolean existByEmail(String email);
    
}
