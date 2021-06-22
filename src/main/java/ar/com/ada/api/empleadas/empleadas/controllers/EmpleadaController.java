package ar.com.ada.api.empleadas.empleadas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada.EstadoEmpleadoEnum;
import ar.com.ada.api.empleadas.empleadas.models.request.InfoEmpleadaNueva;
import ar.com.ada.api.empleadas.empleadas.models.responce.GenericResponce;
import ar.com.ada.api.empleadas.empleadas.services.CategoriaService;
import ar.com.ada.api.empleadas.empleadas.services.EmpleadaService;

@RestController
public class EmpleadaController {

    @Autowired
    private EmpleadaService service;

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleada(@RequestBody InfoEmpleadaNueva empleadaInfo){

        GenericResponce r = new GenericResponce();
        
        Empleada empleada = new Empleada(empleadaInfo.nombre, empleadaInfo.edad, empleadaInfo.sueldo);
        empleada.setFechaAlta(new Date());

        Categoria categoria = categoriaService.buscarCategoria(empleadaInfo.categoriaId);
        empleada.setCategoria(categoria);
        empleada.setEstado(EstadoEmpleadoEnum.ACTIVO);
        

        service.crearEmpleada(empleada);

        r.isOk = true;
        r.id = empleada.getEmpleadaId();
        r.message = "La empleada ha sido creada con exito";

        return ResponseEntity.ok(r);
    
       
    }
    /*@GetMapping("/empleados")
    public ResponseEntity<List<Empleada>> traerEmpleadas(){
        return ResponseEntity.ok(service.traerEmpleadas());
    }*/ //ambas maneras de hacer el codigo estan bien
    
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleada>> traerEmpleadas() {
        List<Empleada> lista = service.traerEmpleadas();

        return ResponseEntity.ok(lista);
    }

}
