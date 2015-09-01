/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.bos.InformeDiario;
import com.losalpes.bos.Reporte;
import com.losalpes.bos.ReporteMueble;
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
        
        reporte.setInformeMuebles(getReporteMuebles());
        
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
        
        reporte.setInformeMuebles(getReporteMuebles());
        
        return reporte;
    }

    @Override
    public List<ReporteMueble> getReporteMuebles() {
        
        List<ReporteMueble> muebles = new ArrayList<>();
        
        //Agrega los muebles del sistema
        muebles.add(new ReporteMueble("RF1",(int)(Math.random() * 10000)));
        muebles.add(new ReporteMueble("RF2",(int)(Math.random() * 10000)));
        muebles.add(new ReporteMueble("RF3",(int)(Math.random() * 10000)));
        muebles.add(new ReporteMueble("RF4",(int)(Math.random() * 10000)));
        muebles.add(new ReporteMueble("RF5",(int)(Math.random() * 10000)));
        muebles.add(new ReporteMueble("RF6",(int)(Math.random() * 10000)));
        muebles.add(new ReporteMueble("RF7",(int)(Math.random() * 10000)));
        muebles.add(new ReporteMueble("RF8",(int)(Math.random() * 10000)));        
        return muebles;
    }
}
