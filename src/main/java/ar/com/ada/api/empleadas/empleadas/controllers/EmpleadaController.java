package ar.com.ada.api.empleadas.empleadas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada.EstadoEmpleadoEnum;
import ar.com.ada.api.empleadas.empleadas.models.request.InfoEmpleadaNueva;
import ar.com.ada.api.empleadas.empleadas.models.request.SueldoNuevoEmpleada;
import ar.com.ada.api.empleadas.empleadas.models.responce.GenericResponse;
import ar.com.ada.api.empleadas.empleadas.services.CategoriaService;
import ar.com.ada.api.empleadas.empleadas.services.EmpleadaService;

@RestController
public class EmpleadaController {

    @Autowired
    private EmpleadaService service;

    @Autowired
    CategoriaService categoriaService;

    /*
     * @GetMapping("/empleados") public ResponseEntity<List<Empleada>>
     * traerEmpleadas(){ return ResponseEntity.ok(service.traerEmpleadas()); }
     */ // ambas maneras de hacer el codigo estan bien

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleada>> traerEmpleadas() {
        List<Empleada> lista = service.traerEmpleada();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> agregarEmpleada(@RequestBody InfoEmpleadaNueva empleadaInfo) {
        GenericResponse respuesta = new GenericResponse();

        Empleada empleada = service.agregarEmpleada(empleadaInfo.nombre, empleadaInfo.edad, empleadaInfo.sueldo,
                empleadaInfo.categoriaId);
        respuesta.isOk = true;
        respuesta.message = "La empleada fue creada con exito";
        respuesta.id = empleada.getEmpleadaId();

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleada> getEmpleadaPorId(@PathVariable Integer id) { // el nombre de la variable debe ser
                                                                                 // igual al nombre del path variable de
                                                                                 // la ruta

        Empleada empleada = service.buscarEmpleada(id);

        return ResponseEntity.ok(empleada);
    }

    // si no son iguales quedaria asi en el parametro (@PathVariable(name = "id
    // ")Interger empleadaId)
    // ver como se hace para que devuelva un 404 si no encontro el nombre en vez de
    // un 200 con null

    // Delete/empleados/{id} -- da de baja un empleado poniendo el campo estado en
    // "baja" y
    // la fecha de baja que sea el dia actual
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<GenericResponse> bajaEmpleada(@PathVariable Integer id) {
        service.bajaEmpleada(id);

        GenericResponse r = new GenericResponse();

        r.isOk = true;
        r.message = "Este empleado ha sido dado de baja correctamente";

        return ResponseEntity.ok(r);

    }

    @GetMapping("/empleados/categorias/{catId}")
    public ResponseEntity<List<Empleada>> optenerEmpleadasPorCategoria(@PathVariable Integer catId) {

        List<Empleada> empleadas = service.traerEmpleadaPorCategoria(catId);
        return ResponseEntity.ok(empleadas);
    }

    @PutMapping("/empleados/{id}/sueldos")
    public ResponseEntity<GenericResponse> modificarSueldo(@PathVariable Integer id,
            @RequestBody SueldoNuevoEmpleada sueldoNuevo) {

        service.modificarSueldo(id, sueldoNuevo);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Sueldo actualizado";

        return ResponseEntity.ok(r);

    }

}
