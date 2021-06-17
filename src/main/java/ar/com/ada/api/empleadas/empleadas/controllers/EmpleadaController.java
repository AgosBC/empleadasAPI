package ar.com.ada.api.empleadas.empleadas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.models.responce.GenericResponce;
import ar.com.ada.api.empleadas.empleadas.services.EmpleadaService;

@Controller
public class EmpleadaController {

    /*@Autowired
    private EmpleadaService service;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleada(@RequestBody Empleada empleada){

        GenericResponce r = new GenericResponce();

        service.crearEmpleada(empleada);

        r.isOk = true;
        r.id = empleada.getEmpleadaId();
        r.message = "La empleada ha sido creada con exito";

        return ResponseEntity.ok(r);
    

        
    }*/
    
}
