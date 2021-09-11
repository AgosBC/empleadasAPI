package ar.com.ada.api.empleadas.empleadas.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
    //@Query("select cat from Categoria cat order by nombre")
    //List<Categoria> findAllOrderByNombre();

    Categoria findByCategoriaId(Integer id);

}
