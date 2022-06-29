
package com.example.crudSpring.controller;

import antlr.StringUtils;
import com.example.crudSpring.dto.Mensajes;
import com.example.crudSpring.dto.ProductoDto;
import com.example.crudSpring.entity.Producto;
import com.example.crudSpring.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/producto")
@CrossOrigin()
public class ProductoController {

    //inyecto servicio
    
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> List(){
        List<Producto> list = productoService.list();
        return new ResponseEntity<List<Producto>>(list, HttpStatus.OK);
        
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id ){
        if(!productoService.existsById(id))
             return new ResponseEntity(new Mensajes("No existe el producto"), HttpStatus.NOT_FOUND);
       
        Producto producto = productoService.getOne(id).get();
             return new ResponseEntity(producto, HttpStatus.OK);
        
    }
    
      @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre ){
        if(!productoService.existsByNombre(nombre))
             return new ResponseEntity(new Mensajes("No existe el producto"), HttpStatus.NOT_FOUND);
       
        Producto producto = productoService.getByNombre(nombre).get();
             return new ResponseEntity(producto, HttpStatus.OK);
        
    }
     
    //notacion@PreAuthorize limito el acceso segun  rol
    
    @PreAuthorize("HasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto) {
        
      //  if(StringUtils.isBlank(productoDto.getNombre())   )
        //    return new ResponseEntity(new Mensajes("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()<=0)
            return new ResponseEntity(new Mensajes("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(productoDto.getNombre()))   
            return new ResponseEntity(new Mensajes("El nombre ya existe,elija otro"), HttpStatus.BAD_REQUEST);
       
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio() );
        productoService.save(producto);
        return new ResponseEntity(new Mensajes("Producto creado"), HttpStatus.OK);
    }
    
    @PreAuthorize("HasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id,   @RequestBody ProductoDto productoDto) {
        if(!productoService.existsById(id))
             return new ResponseEntity(new Mensajes("No existe el producto"), HttpStatus.NOT_FOUND);
      //  if(StringUtils.isBlank(productoDto.getNombre())   )
        //    return new ResponseEntity(new Mensajes("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()<=0)
            return new ResponseEntity(new Mensajes("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
       //comprueba que no duplique el nombre, pero que si se pueda actualizar otros campos(aunque el nombre E)(obteng prod obtengo nombre obt id del nombre si es != al ppal es otro)
        if(productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)   
            return new ResponseEntity(new Mensajes("El nombre ya existe,elija otro"), HttpStatus.BAD_REQUEST);
       
        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        
        
        
        return new ResponseEntity(new Mensajes("Producto actualizado"), HttpStatus.OK);
    }
    
    @PreAuthorize("HasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!productoService.existsById(id))
             return new ResponseEntity(new Mensajes("No existe el producto"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensajes("Producto eliminado"), HttpStatus.OK);
    }
    
}
