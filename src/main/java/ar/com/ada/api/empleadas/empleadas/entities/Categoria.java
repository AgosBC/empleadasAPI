package ar.com.ada.api.empleadas.empleadas.entities;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;
;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer categoriaId;

    private String nombre;

    @Column(name = "sueldo_base")
    private BigDecimal sueldoBase;

    //creo la lista empleados que luego usare para el join de la FK con mapeo one to many

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Empleada> empleadas = new ArrayList<>();

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public List<Empleada> getEmpleadas() {
        return empleadas;
    }

    public void setEmpleadas(List<Empleada> empleadas) {
        this.empleadas = empleadas;
    }
    
    public void agregarEmpleada (Empleada empleada){
        this.empleadas.add(empleada);
    }
}
