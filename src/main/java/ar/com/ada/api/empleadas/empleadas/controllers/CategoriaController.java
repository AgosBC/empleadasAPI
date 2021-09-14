package ar.com.ada.api.empleadas.empleadas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.models.responce.GenericResponse;
import ar.com.ada.api.empleadas.empleadas.services.CategoriaService;
import ar.com.ada.api.empleadas.empleadas.services.CategoriaService.ValidacionCategoriaEnum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController // anotacion
public class CategoriaController {

    @Autowired // anotacion
    private CategoriaService service;

    @PostMapping("/categorias") // mapping url
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) { 

        GenericResponse respuesta = new GenericResponse();
        ValidacionCategoriaEnum catValida = service.validar(categoria);
        
        if (catValida.equals(ValidacionCategoriaEnum.OK)){
            service.crearCategoria(categoria);

            respuesta.isOk = true;
            respuesta.id = categoria.getCategoriaId();
            respuesta.message = "La categoria fue creada con exito";

            return ResponseEntity.ok(respuesta);            
        
        }else {

            respuesta.isOk = false;
            respuesta.message = catValida.toString();
            return ResponseEntity.badRequest().body(respuesta);
        }

    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<GenericResponse> borrarCat(@PathVariable Integer id){
        GenericResponse rta = new GenericResponse();
        service.borrarCat(id);
        rta.isOk = true;
        rta.message = "la categoria ha sido eliminada";

        return ResponseEntity.ok(rta);
        
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> traerCategorias() {
        return ResponseEntity.ok(service.traerCategorias());
    }

    @GetMapping(value="/categoria/sueldos-nuevos")
    public ResponseEntity<List<Empleada>> calcularProximosSueldos() {
        return ResponseEntity.ok(service.calcularProximosSueldosSinStream());
    }
    
    
    @GetMapping("/categorias/sueldos-actuales")// devuelve exactamente lo mismo que el get de empleados. lista de empleados completa. probar discriminar, 
                                                // modificar traerEmpleados o este metodo, que traigan info diferente c/u con un model (este metodo sin endpoint en postman)
    public ResponseEntity<List<Empleada>> obtenerSueldosActuales() {
        return ResponseEntity.ok(service.obtenerSueldosActuales());
    }

    @GetMapping("/categorias/sin-empleados")
    public ResponseEntity<List<Categoria>> obtenerCategoriasSinEmpleadas() {
        return ResponseEntity.ok(service.obtenerCategoriasSinEmpleadas());
    }

    @GetMapping("/categorias/minimo-sueldo")
    public ResponseEntity<Categoria> obtenerCategoriaConMinimoSueldo() {
        return ResponseEntity.ok(service.obtenerCategoriaConMinimoSueldo());
    }

    @GetMapping("/categorias/nombres")
    public ResponseEntity<List<String>> obtenerNombresCategorias() {
        return ResponseEntity.ok(service.obtenerNombresCategorias());
    }

}
