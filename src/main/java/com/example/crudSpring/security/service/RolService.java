
package com.example.crudSpring.security.service;

import com.example.crudSpring.security.entity.Rol;
import com.example.crudSpring.security.enums.RolNombre;
import com.example.crudSpring.security.repository.RolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
 
    @Autowired
    RolRepository rolRepository;
    
    public Optional<Rol> findByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
}
