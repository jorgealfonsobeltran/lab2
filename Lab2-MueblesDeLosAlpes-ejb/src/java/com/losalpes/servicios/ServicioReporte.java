/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.bos.InformeDiario;
import com.losalpes.bos.Reporte;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

/**
 *
 * @author Camilo Marroquin
 */
public class ServicioReporte implements IServicioReporte{

    @Override
    public Reporte getReporte(Calendar fechaInicio, Calendar fechaFin) {
        
        Reporte reporte = new Reporte();
        
        long diferenciaTicks = fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();
        long diferenciaDias = diferenciaTicks / (1000 * 60 * 60 * 24);
        
                
        List<InformeDiario> lista = new ArrayList<>();
        
        for (int i = 0; i <= diferenciaDias; i++) {
            Calendar fecha =  Calendar.getInstance();
            fecha.setTime(fechaInicio.getTime());
            int random1 = (int)(Math.random() * 10000);
            int random2 = (int)(Math.random() * 10000);
            fecha.add(Calendar.DAY_OF_YEAR, i);
            InformeDiario informeDiario = new InformeDiario(fecha, random1, random2, i*1200, i*1300);
            informeDiario.setFecha(fecha);    
            lista.add(informeDiario);       
        }
        
        reporte.setInformeDiarios(lista);
        
        return reporte;
    }

    @Override
    public Reporte getReporteCliente(String identificacion, Calendar fechaInicio, Calendar fechaFin) {
        Reporte reporte = new Reporte();
        
        long diferenciaTicks = fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();
        long diferenciaDias = diferenciaTicks / (1000 * 60 * 60 * 24);
        
                
        List<InformeDiario> lista = new ArrayList<>();
        
        for (int i = 0; i <= diferenciaDias; i++) {
            Calendar fecha =  Calendar.getInstance();
            fecha.setTime(fechaInicio.getTime());
            int random1 = (int)(Math.random() * 10000);
            int random2 = (int)(Math.random() * 10000);
            fecha.add(Calendar.DAY_OF_YEAR, i);
            InformeDiario informeDiario = new InformeDiario(fecha, random1, random2, i*1200, i*1300);
            informeDiario.setFecha(fecha);    
            lista.add(informeDiario);       
        }
        
        reporte.setInformeDiarios(lista);
        
        return reporte;
    }
}
