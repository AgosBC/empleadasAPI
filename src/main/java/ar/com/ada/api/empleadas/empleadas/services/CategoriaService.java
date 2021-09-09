package ar.com.ada.api.empleadas.empleadas.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.empleadas.entities.*;
import ar.com.ada.api.empleadas.empleadas.repos.*;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepo;

    public void crearCategoria(Categoria categoria) { // void o boolenan

        categoriaRepo.save(categoria);
    }

    public List<Categoria> traerCategorias() {
        return categoriaRepo.findAllOrderByNombre();
    }

    public Categoria buscarCategoria(Integer categoriaId) {
        Optional<Categoria> resultado = categoriaRepo.findById(categoriaId);
        Categoria categoria = null;

        if (resultado.isPresent())
            categoria = resultado.get();

        return categoria;
    }

    
    public List<Empleada> calcularProximosSueldosSinStream() {
        List<Empleada> listaEmpleadas = new ArrayList<>();
        for (Categoria categoria : this.traerCategorias()) {
            for (Empleada empleada : categoria.getEmpleadas()) {
                empleada.setSueldo(categoria.calcularProximoSueldo(empleada));
                listaEmpleadas.add(empleada);
                
            }

            
        }
        return listaEmpleadas;
    }

    // stream --> flujo de objetos --- paradigma funcional
    public List<Empleada> calcularProximosSueldos(){

        List<Empleada> listaEmpleadas = new ArrayList<>();

        this.traerCategorias().stream().forEach(categoria -> {
            categoria.getEmpleadas().stream().forEach(empleada -> {
                empleada.setSueldo(categoria.calcularProximoSueldo(empleada));
                listaEmpleadas.add(empleada);
            });
        });
       
        return listaEmpleadas;


    } 



}
