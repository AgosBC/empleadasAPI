package ar.com.ada.api.empleadas.empleadas.services;

import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada.EstadoEmpleadoEnum;
import ar.com.ada.api.empleadas.empleadas.exceptions.ResourceNotFoundException;
import ar.com.ada.api.empleadas.empleadas.models.request.InfoEmpleadaNueva;
import ar.com.ada.api.empleadas.empleadas.models.request.SueldoNuevoEmpleada;
import ar.com.ada.api.empleadas.empleadas.repos.EmpleadaRepository;


@Service
public class EmpleadaService {

    @Autowired
    EmpleadaRepository empleadaRepo;

    @Autowired
    CategoriaService categoriaService;

    public Empleada agregarEmpleada(String nombre, int edad, BigDecimal sueldo, Integer categoriaId) {
       
        Empleada empleada = new Empleada(nombre.toLowerCase(), edad, sueldo, new Date());
    
        Categoria categoria = categoriaService.buscarCategoria(categoriaId);
        empleada.setCategoria(categoria);
        empleada.setEstado(EstadoEmpleadoEnum.ACTIVO);
        empleadaRepo.save(empleada);
        return empleada;
    }

     

    public void crearEmpleada(Empleada empleada) {

        empleadaRepo.save(empleada);

    }

    public List<Empleada> traerEmpleadas() {
        return empleadaRepo.findAll();
    }

    public List<Empleada> traerEmpleada() {
        List<Empleada> empleados = empleadaRepo.findAll();

        List<Empleada> listaFiltrada = new ArrayList<>();

        for (Empleada empleado : empleados) {
            if (!empleado.getEstado().equals(EstadoEmpleadoEnum.BAJA)) {
                listaFiltrada.add(empleado);
            }
        }

        return listaFiltrada;
    }

    public Empleada buscarEmpleada(Integer empleadaId) {
        
        return empleadaRepo.findByEmpleadaId(empleadaId);
        
        /*Optional<Empleada> resultado = empleadaRepo.findById(empleadaId);

        if (resultado.isPresent())
            return resultado.get();

        return null;*/

    }

    // DELEETE LOGICO eliminacion logica. no se elimina de la base de datos, se da
    // de baja
    public void bajaEmpleada(Integer id) {
        Empleada empleada = this.buscarEmpleada(id);
        empleada.setEstado(EstadoEmpleadoEnum.BAJA);
        empleada.setFechaBaja(new Date());

        empleadaRepo.save(empleada);
    }

    public List<Empleada> traerEmpleadaPorCategoria(Integer catId) {
        Categoria categoria = categoriaService.buscarCategoria(catId);

        return categoria.getEmpleadas();
    }

    public void guardar(Empleada empleada) {
        empleadaRepo.save(empleada);
    }

    public void modificarSueldo(Integer id, SueldoNuevoEmpleada sueldoNuevoInfo) {

        Empleada empleada = this.buscarEmpleada(id);
        empleada.setSueldo(sueldoNuevoInfo.sueldoNuevo);
        this.guardar(empleada);
    }

    public List<Empleada> buscarEmpleadoPorNombre(String nombre) {
        return empleadaRepo.findByNombre(nombre);
    }


    

}
