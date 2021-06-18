package ar.com.ada.api.empleadas.empleadas.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.empleadas.empleadas.entities.Empleada;

@Repository
public interface EmpleadaRepository extends JpaRepository<Empleada, Integer> {
    
}






