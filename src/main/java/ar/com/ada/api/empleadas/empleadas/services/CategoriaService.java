package ar.com.ada.api.empleadas.empleadas.services;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<Empleada> obtenerSueldosActuales(){
        List<Empleada> listaEmpleada = new ArrayList<>();

        traerCategorias().stream().forEach(cat -> listaEmpleada.addAll(cat.getEmpleadas()));
        return listaEmpleada;
    }

    public List<Categoria> obtenerCategoriasSinEmpleadas() {

        return traerCategorias().stream().filter(categoria -> categoria.getEmpleadas().size() == 0) //lambda -> (traer kas empleadas .size ==0 AKA ninguna)
        .collect(Collectors.toList()); // de stram volver a pasarlo a lista
    }

    public Categoria obtenerCategoriaConMinimoSueldo() {
        return traerCategorias().stream().min(Comparator.comparing(Categoria :: getSueldoBase)).get();
    }

    public List<String> obtenerNombresCategorias(){
        
        return this.traerCategorias().stream().map(cat -> cat.getNombre()).collect(Collectors.toList());

        
    }





}
