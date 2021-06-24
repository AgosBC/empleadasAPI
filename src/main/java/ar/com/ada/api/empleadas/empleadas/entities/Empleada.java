package ar.com.ada.api.empleadas.empleadas.entities;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "empleada")
public class Empleada {
   
    public Empleada() {      
    }//hacer un constructor vacio vacio. para solucionar el problema"defoult constructor not found"
    //esto sucede por que hibernate al creae las entidades desde la base de datos
    //no sabe y usa constructor por defecto lo mismo pasa con el framework de Json a entity
    // sin un contructor vacio se rompe todo
    //Jpa repository empleadas instancia una nueva empleada con un contructor, que al no saber 
    // que parametros usar, usa uno vacio. al crear un constructor nuevo, ese por defecto desaparece
    //y hibernate no entiende como crear/instanciar sin ese contructor vacio. como solucion lo creamos
    //lo mismo pasa desde Postman json

    public Empleada(String nombre, Integer edad, BigDecimal sueldo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sueldo = sueldo;
        this.setEstado(EstadoEmpleadoEnum.ACTIVO);
        this.setFechaAlta(new Date());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleada_id")
    private Integer empleadaId;
    
   
    private String nombre;

    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    private Categoria categoria;

    private BigDecimal sueldo;

    @Column(name = "estado_id")
    private int estado;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_baja")
    private Date fechaBaja;
    

    public Integer getEmpleadaId() {
        return empleadaId;
    }

    public void setEmpleadaId(Integer empleadaId) {
        this.empleadaId = empleadaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        this.categoria.agregarEmpleada(this); // para agregarle una categoria al empleado
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    //enum estado

    public enum EstadoEmpleadoEnum{

        ACTIVO(1),
        INACTIVO(2),
        BAJA(3);

        private final int value;

        private EstadoEmpleadoEnum(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static EstadoEmpleadoEnum parse(int id){
            EstadoEmpleadoEnum status = null;
            for(EstadoEmpleadoEnum item : EstadoEmpleadoEnum.values()){
                if(item.getValue() == id){
                    status = item;
                    break;

                }
            }

            return status;  
        }

      
    }
     // Cambiar getter  setter del int estado por estos del enumerado
     
    public EstadoEmpleadoEnum getEstado(){
        return EstadoEmpleadoEnum.parse(this.estado);
        
    }

    public void setEstado(EstadoEmpleadoEnum estado){
        this.estado = estado.getValue();
    }

   
}
