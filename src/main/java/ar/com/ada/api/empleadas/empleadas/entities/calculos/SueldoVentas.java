package ar.com.ada.api.empleadas.empleadas.entities.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ar.com.ada.api.empleadas.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.empleadas.entities.Empleada;

public class SueldoVentas implements ISueldoCalculator{

    /*private Categoria categoria;
    @Override
    public void init(Categoria categoria) {
        this.categoria = categoria;
        
    }*/

    @Override
    public BigDecimal calcularSueldo(Empleada empleada) {
        BigDecimal ventasAnuales = empleada.obtenerVentasAnuales();
        
        return empleada.getCategoria().getSueldoBase().add(ventasAnuales.multiply(new BigDecimal(0.1))).setScale(2, RoundingMode.HALF_EVEN); //half even redondeo bancario
    }

   
    
}
