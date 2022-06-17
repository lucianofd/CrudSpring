
package com.example.crudSpring.service;

import com.example.crudSpring.entity.Producto;
import com.example.crudSpring.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;
    
    public List<Producto> list(){
        return productoRepository.findAll();
    }
    
    public Optional<Producto> getOne(int id){
        return productoRepository.findById(id);
    }
    
    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }
    
    public void save(Producto producto){
        productoRepository. save(producto);
    }
    
    public void delete (int id){
        productoRepository.deleteById(id);
    }
    
    public boolean existsById (int id){
        return productoRepository.existsById(id);
    }
    
    public boolean existsByNombre (String nombre){
        return productoRepository.existsByNombre(nombre);
    }
}
