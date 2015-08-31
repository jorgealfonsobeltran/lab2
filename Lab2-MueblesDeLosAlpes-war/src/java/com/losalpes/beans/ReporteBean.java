/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.bos.InformeDiario;
import com.losalpes.bos.Reporte;
import com.losalpes.servicios.IServicioReporte;
import com.losalpes.servicios.ServicioReporte;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
 
import org.primefaces.model.chart.*;

/**
 *
 * @author Camilo Marroquin
 */
 
@ManagedBean
@SessionScoped
public class ReporteBean implements Serializable{
    
    private BarChartModel barModel;
    
    private HorizontalBarChartModel horizontalBarModel;
    
    private LineChartModel dateModel;
    
    private Reporte reporte;
    
    private IServicioReporte servicioReporte;
    
    private Date fechaInicial;
    
    private Date fechaFinal;

    private SimpleDateFormat sdf;
    
    @PostConstruct
    public void init() {
        servicioReporte = new ServicioReporte();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        
        fechaFinal = fechaFinal == null? new Date(): fechaFinal;
       
        
        Calendar fechaF = Calendar.getInstance();
        Calendar fechaI = Calendar.getInstance();
        fechaF.setTime(fechaFinal);
        fechaI.setTime(fechaFinal);
        
        fechaI.add(Calendar.DAY_OF_YEAR, -15);
        fechaInicial = fechaInicial == null ?  fechaI.getTime(): fechaInicial;
        
        reporte = servicioReporte.getReporte(fechaI, fechaF);
        
        createBarModels();
    }
    
    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel () {
        return horizontalBarModel;
    }
    
    public LineChartModel getDateModel() {
        return dateModel;
    }
 
    private BarChartModel initBarModel()  {
        BarChartModel model = new BarChartModel();
 
        ChartSeries mueblesI = new ChartSeries();
        ChartSeries mueblesE = new ChartSeries();
        
        mueblesI.setLabel("Muebles Interior");
        mueblesE.setLabel("Muebles Exterior");
        
        for (InformeDiario item : reporte.getInformeDiarios()) {
            mueblesI.set(sdf.format(item.getFecha().getTime()), item.getMueblesInterior());
            mueblesE.set(sdf.format(item.getFecha().getTime()), item.getMueblesExterior());
        }
 
        model.addSeries(mueblesI);
        model.addSeries(mueblesE);
         
            return model;
    }
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
        createDateModel();
    }
     
    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries mueblesI = new LineChartSeries();
        LineChartSeries mueblesE = new LineChartSeries();
        
        mueblesI.setLabel("Muebles Interior");
        mueblesE.setLabel("Muebles Exterior");
        
        for (InformeDiario item : reporte.getInformeDiarios()) {
            mueblesI.set(sdf.format(item.getFecha().getTime()), item.getMueblesInterior());
            mueblesE.set(sdf.format(item.getFecha().getTime()), item.getMueblesExterior());
        }
 
        dateModel.addSeries(mueblesI);
        dateModel.addSeries(mueblesE);
         
        dateModel.setTitle("Reporte Diario");
        dateModel.setZoom(true);
        dateModel.setAnimate(true);
        dateModel.getAxis(AxisType.Y).setLabel("Muebles");
        DateAxis axis = new DateAxis("Fechas");
        dateModel.setLegendPosition("e");
        dateModel.setShowPointLabels(true);
        axis.setTickAngle(-50);
//        axis.setMax("2014-02-01");
//        axis.setTickFormat("%b %#d, %y");
         
        dateModel.getAxes().put(AxisType.X, axis);
    }
    
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Reporte numero de muebles");
        barModel.setLegendPosition("ne");
         
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
//        axis.setMax("2014-02-01");
//        axis.setTickFormat("%b %#d, %y");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Muebles");
        
        barModel.getAxes().put(AxisType.X, axis);
    }
     
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");    
    }
    public void consultarReporte(){
        Calendar fechaF = Calendar.getInstance();
        Calendar fechaI = Calendar.getInstance();
        fechaF.setTime(fechaFinal);
        fechaI.setTime(fechaInicial);
        reporte= servicioReporte.getReporte(fechaI, fechaF);
        createBarModels();
    }
}
