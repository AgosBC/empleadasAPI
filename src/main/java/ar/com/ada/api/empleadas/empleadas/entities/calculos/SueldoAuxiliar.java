package ar.com.ada.api.empleadas.empleadas.entities.calculos;

import java.math.BigDecimal;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;

public class SueldoAuxiliar implements ISueldoCalculator {

    
    /*private Categoria categoria;
    @Override
    public void init(Categoria categoria) {
        this.categoria = categoria;
        
    }*/

    @Override
    public BigDecimal calcularSueldo(Empleada empleada) {
        return empleada.getCategoria().getSueldoBase();
    }

  
    
}
