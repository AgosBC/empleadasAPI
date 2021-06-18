package ar.com.ada.api.empleadas.empleadas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.repos.EmpleadaRepository;

@Service
public class EmpleadaService {

    @Autowired
    private EmpleadaRepository empleadaRepo;

    public void crearEmpleada(Empleada empleada){

        empleadaRepo.save(empleada);                
    }
    
    public List<Empleada> traerEmpleadas(){
        return empleadaRepo.findAll();
    }
}
