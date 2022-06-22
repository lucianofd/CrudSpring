
package com.example.crudSpring.security.service;

import com.example.crudSpring.security.entity.Usuario;
import com.example.crudSpring.security.repository.UsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existByNombreUsuario(nombreUsuario);
    }
    
    public boolean existByEmail(String email){
       return usuarioRepository.existByEmail(email);
    }
    
    //guardado
    public void save (Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
