package ar.com.ada.api.empleadas.empleadas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.models.responce.GenericResponse;
import ar.com.ada.api.empleadas.empleadas.services.CategoriaService;

@RestController // anotacion
public class CategoriaController {

    @Autowired // anotacion
    private CategoriaService service;

    @PostMapping("/categorias") // mapping url
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) { 

        GenericResponse respuesta = new GenericResponse();

        service.crearCategoria(categoria);

        respuesta.isOk = true;
        respuesta.id = categoria.getCategoriaId();
        respuesta.message = "La categoria fue creada con exito";

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> traerCategorias() {
        return ResponseEntity.ok(service.traerCategorias());
    }

}
