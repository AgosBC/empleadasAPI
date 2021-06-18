package ar.com.ada.api.empleadas.empleadas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.models.responce.GenericResponce;
import ar.com.ada.api.empleadas.empleadas.services.EmpleadaService;

@RestController
public class EmpleadaController {

    @Autowired
    public EmpleadaService service;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleada(@RequestBody Empleada empleada){

        GenericResponce r = new GenericResponce();

        service.crearEmpleada(empleada);

        r.isOk = true;
        r.id = empleada.getEmpleadaId();
        r.message = "La empleada ha sido creada con exito";

        return ResponseEntity.ok(r);
    
       
    }
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleada>> traerEmpleadas(){
        return ResponseEntity.ok(service.traerEmpleadas());
    }
    
}
