package ar.com.ada.api.empleadas.empleadas.entities.calculos;

import java.math.BigDecimal;

import ar.com.ada.api.empleadas.empleadas.entities.Empleada;

public interface ISueldoCalculator {

    BigDecimal calcularSueldo(Empleada empleada);
    
}
