package ar.com.ada.api.empleadas.empleadas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.empleadas.entities.*;
import ar.com.ada.api.empleadas.empleadas.repos.*;


@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepo;

    public void crearCategoria(Categoria categoria){ //void o boolenan

        categoriaRepo.save(categoria);
    }
    



    // para acortar codigo sera que se usa
   /* public void grabar(Categoria categoria){
        categoriaRepo.save(categoria)
    }*/
    // entonces en el metodo de arriba en la linea 18 iria grabar(categoria) y si es boolean un return true


    public List<Categoria> traerCategorias(){
        return categoriaRepo.findAll();
    }

    public Categoria buscarCategoria(Integer categoriaId){
        Optional<Categoria> resultado = categoriaRepo.findById(categoriaId);
        Categoria categoria = null;

        if (resultado.isPresent())
            categoria = resultado.get();
        
        return categoria;
    }





}
