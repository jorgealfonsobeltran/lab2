/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.bos.InformeDiario;
import com.losalpes.bos.Reporte;
import com.losalpes.bos.TipoUsuario;
import com.losalpes.bos.Usuario;
import com.losalpes.servicios.IServicioReporte;
import com.losalpes.servicios.ServicioReporte;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
 
import org.primefaces.model.chart.*;

/**
 *
 * @author Camilo Marroquin
 */
 
@ManagedBean
@SessionScoped
public class ReporteBean implements Serializable{
    
    private LineChartModel dateClienteModel;
    
    private HorizontalBarChartModel horizontalBarModel;
    
    private LineChartModel dateModel;
    
    private Reporte reporte;
    
    private IServicioReporte servicioReporte;
    
    private Date fechaInicial;
    
    private Date fechaFinal;
    
    private Date fechaInicialCliente;
    
    private Date fechaFinalCliente;
    
    private Usuario usuario;
    
    private List<Usuario> usuarios;

    private SimpleDateFormat sdf;
    
    private List<SelectItem> clientesItems;
    @PostConstruct
    public void init() {
        
        servicioReporte = new ServicioReporte();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        usuarios.add(new Usuario("Angela Suarez", "122345", TipoUsuario.CLIENTE));
        usuarios.add(new Usuario("Jorge Beltran", "122345", TipoUsuario.CLIENTE));
        usuarios.add(new Usuario("Camilo Marroquin", "122345", TipoUsuario.CLIENTE));
        
        fechaFinal = fechaFinal == null? new Date(): fechaFinal;
       
        Calendar fechaF = Calendar.getInstance();
        Calendar fechaI = Calendar.getInstance();
        fechaF.setTime(fechaFinal);
        fechaI.setTime(fechaFinal);
        
        fechaI.add(Calendar.DAY_OF_YEAR, -15);
        fechaInicial = fechaInicial == null ?  fechaI.getTime(): fechaInicial;
        
        reporte = servicioReporte.getReporte(fechaI, fechaF);
        
        createModels();
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
    
    public Date getFechaInicialCliente() {
        return fechaInicialCliente;
    }

    public void setFechaInicialCliente(Date fechaInicialCliente) {
        this.fechaInicialCliente = fechaInicialCliente;
    }

    public Date getFechaFinalCliente() {
        return fechaFinalCliente;
    }

    public void setFechaFinalCliente(Date fechaFinalCliente) {
        this.fechaFinalCliente = fechaFinalCliente;
    }

    public List<Usuario> getUsuarios(){
        return usuarios;
    }

    public void setClientes(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel () {
        return horizontalBarModel;
    }
    
    public LineChartModel getDateModel() {
        return dateModel;
    }
    
    public LineChartModel getDateClienteModel() {
        return dateClienteModel;
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
     
    private void createModels() {
        createDateClienteModel();
        createHorizontalVentasModel();
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
        dateModel.getAxis(AxisType.Y).setMin(0);
        DateAxis axis = new DateAxis("Fechas");
        dateModel.setLegendPosition("e");
        dateModel.setShowPointLabels(true);
        axis.setTickAngle(-50);
         
        dateModel.getAxes().put(AxisType.X, axis);
    }
    
    private void createDateClienteModel() {
        dateClienteModel = new LineChartModel();
        LineChartSeries mueblesI = new LineChartSeries();
        LineChartSeries mueblesE = new LineChartSeries();
        
        mueblesI.setLabel("Muebles Interior");
        mueblesE.setLabel("Muebles Exterior");
        
        for (InformeDiario item : reporte.getInformeDiarios()) {
            mueblesI.set(sdf.format(item.getFecha().getTime()), item.getMueblesInterior());
            mueblesE.set(sdf.format(item.getFecha().getTime()), item.getMueblesExterior());
        }
 
        dateClienteModel.addSeries(mueblesI);
        dateClienteModel.addSeries(mueblesE);
         
        dateClienteModel.setTitle("Reporte Diario");
        dateClienteModel.setZoom(true);
        dateClienteModel.setAnimate(true);
        dateClienteModel.getAxis(AxisType.Y).setLabel("Muebles");
        dateClienteModel.getAxis(AxisType.Y).setMin(0);
        DateAxis axis = new DateAxis("Fechas");
        dateClienteModel.setLegendPosition("e");
        dateClienteModel.setShowPointLabels(true);
        axis.setTickAngle(-50);
         
        dateClienteModel.getAxes().put(AxisType.X, axis);
    }
     
    private void createHorizontalVentasModel() {
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
        createModels();
    }
    
    public void consultarReporteCliente(){
        Calendar fechaF = Calendar.getInstance();
        Calendar fechaI = Calendar.getInstance();
        fechaF.setTime(fechaFinalCliente);
        fechaI.setTime(fechaInicialCliente);
        reporte= servicioReporte.getReporteCliente("", fechaI, fechaF);
        createModels();
    }
}
