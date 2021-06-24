package ar.com.ada.api.empleadas.empleadas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.empleadas.repos.EmpleadaRepository;

@Service
public class EmpleadaService {

    @Autowired
    EmpleadaRepository empleadaRepo;

    public void crearEmpleada(Empleada empleada){

        empleadaRepo.save(empleada);                
    }
    
    public List<Empleada> traerEmpleadas(){
        return empleadaRepo.findAll();
    }

    public Empleada buscarEmpleada(Integer empleadaId){
        Optional<Empleada> resultado = empleadaRepo.findById(empleadaId);
        
        if (resultado.isPresent())
            return resultado.get();
        
        return null;

              

        
    }
}
