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
import ar.com.ada.api.empleadas.empleadas.models.responce.GenericResponse;
import ar.com.ada.api.empleadas.empleadas.services.CategoriaService;
import ar.com.ada.api.empleadas.empleadas.services.EmpleadaService;

@RestController
public class EmpleadaController {

    @Autowired
    private EmpleadaService service;

    @Autowired
    CategoriaService categoriaService;

    /*@PostMapping("/empleados")//con constructor
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
    
       
    }*/
    /*@GetMapping("/empleados")
    public ResponseEntity<List<Empleada>> traerEmpleadas(){
        return ResponseEntity.ok(service.traerEmpleadas());
    }*/ //ambas maneras de hacer el codigo estan bien
    
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleada>> traerEmpleadas() {
        List<Empleada> lista = service.traerEmpleadas();

        return ResponseEntity.ok(lista);
    }


    
    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleada(@RequestBody InfoEmpleadaNueva empleadaInfo) {
        GenericResponse respuesta = new GenericResponse();

        Empleada empleada = new Empleada(); 
        empleada.setNombre(empleadaInfo.nombre);
        empleada.setEdad(empleadaInfo.edad);
        empleada.setSueldo(empleadaInfo.sueldo);
        empleada.setFechaAlta(new Date());
        
        Categoria categoria = categoriaService.buscarCategoria(empleadaInfo.categoriaId);
        empleada.setCategoria(categoria);
        empleada.setEstado(EstadoEmpleadoEnum.ACTIVO);

        service.crearEmpleada(empleada);
        respuesta.isOk = true;
        respuesta.id = empleada.getEmpleadaId();
        respuesta.message = "La empleada fue creada con exito";
        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleada> getEmpleadaPorId(@PathVariable Integer id){ // el nombre de la variable debe ser igual al nombre del path variable de la ruta
        
        Empleada empleada = service.buscarEmpleada(id);

        return ResponseEntity.ok(empleada);
    }

    //si no son iguales quedaria asi en el parametro (@PathVariable(name = "id ")Interger empleadaId)
    //ver como se hace para que devuelva un 404 si no encontro el nombre en vez de un 200 con null

}
