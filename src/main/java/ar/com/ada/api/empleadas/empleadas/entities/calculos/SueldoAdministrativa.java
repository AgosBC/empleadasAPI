package ar.com.ada.api.empleadas.empleadas.entities.calculos;

import java.math.BigDecimal;
import java.util.List;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;

public class SueldoAdministrativa implements ISueldoCalculator {

    
   /* private Categoria categoria;
    @Override
    public void init(Categoria categoria) {
        this.categoria = categoria;
        
    }*/

    @Override
    public BigDecimal calcularSueldo(Empleada empleada) {
        BigDecimal sueldoBase = empleada.getCategoria().getSueldoBase();
        if (empleada.getSueldo().compareTo(sueldoBase) == -1) {

            return sueldoBase;
            
        }
        return empleada.getSueldo();
    }
    
}
