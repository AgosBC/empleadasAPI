package ar.com.ada.api.empleadas.empleadas.entities.calculos;

import java.math.BigDecimal;
import java.util.List;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;

public interface ISueldoCalculator {

    //public void init(Categoria categoria);

    BigDecimal calcularSueldo(Empleada empleada);
    
}
