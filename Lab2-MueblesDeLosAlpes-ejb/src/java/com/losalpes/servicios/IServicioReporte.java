/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.bos.Reporte;
import java.util.Calendar;

/**
 *
 * @author Camilo Marroquin
 */

/**
 * Contrato funcional de los servicios asociados a las ventas
 */
public interface IServicioReporte {
    
    /**
     * Devuelve los datos del reporte
     * @param fechaInicio
     * @param fechaFin
     * @return 
     */
    public Reporte getReporte(Calendar fechaInicio, Calendar fechaFin);
    
    /**
     * 
     * @param identificacion
     * @param fechaInicio
     * @param fechaFin
     * @return 
     */
    public Reporte getReporteCliente(String identificacion, Calendar fechaInicio, Calendar fechaFin);
    
}
