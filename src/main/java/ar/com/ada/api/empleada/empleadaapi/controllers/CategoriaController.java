package ar.com.ada.api.empleada.empleadaapi.controllers;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.empleada.empleadaapi.entities.Categoria;
import ar.com.ada.api.empleada.empleadaapi.models.responce.GenericResponce;
import ar.com.ada.api.empleada.empleadaapi.services.CategoriaService;

@RestController //anotacion
public class CategoriaController {
    
    @Autowired //anotacion
    private CategoriaService service;

    @PostMapping("/categorias") //mapping url 
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria){ //no es un void una api siempre devuelve algo al 

        GenericResponce respuesta = new GenericResponce();
       

        service.crearCategoria(categoria);

        respuesta.isOk = true;
        respuesta.id = categoria.getCategoriaId();
        respuesta.message = "La categoria fue creada con exito";

        return ResponseEntity.ok(respuesta);


    }

    //get/categorias

    public ResponseEntity<List<Categoria>> traerCategorias(){
        return ResponseEntity.ok(service.traerCategorias());
    }
    
}
