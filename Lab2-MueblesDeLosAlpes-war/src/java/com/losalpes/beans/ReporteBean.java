/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.bos.InformeDiario;
import com.losalpes.bos.Reporte;
import com.losalpes.bos.ReporteMueble;
import com.losalpes.bos.TipoUsuario;
import com.losalpes.bos.Usuario;
import com.losalpes.servicios.IServicioReporte;
import com.losalpes.servicios.ServicioReporte;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class ReporteBean implements Serializable {

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
    private List<SelectItem> items;
    private SimpleDateFormat sdf;

    @PostConstruct
    public void init() {
        items= new ArrayList<>();

        servicioReporte = new ServicioReporte();
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        usuarios = new ArrayList<>();
        usuario = new Usuario();
        usuarios.add(new Usuario("Angela Suarez", "122345", TipoUsuario.CLIENTE));
        usuarios.add(new Usuario("Jorge Beltran", "122345", TipoUsuario.CLIENTE));
        usuarios.add(new Usuario("Camilo Marroquin", "122345", TipoUsuario.CLIENTE));

        fechaFinal = fechaFinal == null ? new Date() : fechaFinal;

        Calendar fechaF = Calendar.getInstance();
        Calendar fechaI = Calendar.getInstance();
        fechaF.setTime(fechaFinal);
        fechaI.setTime(fechaFinal);

        fechaI.add(Calendar.DAY_OF_YEAR, -15);
        fechaInicial = fechaInicial == null ? fechaI.getTime() : fechaInicial;

        reporte = servicioReporte.getReporte(fechaI, fechaF);

        createModels();
        cargarListaClientes();
    }

    public void cargarListaClientes() {
        for (Usuario usuario : usuarios) {
            SelectItem selectItem = new SelectItem(usuario.getNombre(), usuario.getNombre());
            items.add(selectItem);
        }
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public LineChartModel getDateClienteModel() {
        return dateClienteModel;
    }

    private BarChartModel initBarModel() {
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
        
        
        for (ReporteMueble item : reporte.getInformeMuebles()) {
            ChartSeries muebles = new ChartSeries();
            muebles.setLabel(item.getNombre());
            muebles.set("Tipos" , item.getCantidad());
            horizontalBarModel.addSeries(muebles);
        }

        horizontalBarModel.setTitle("Ventas por Mueble");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setZoom(true);
        horizontalBarModel.setAnimate(true);
        
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Ventas");
        xAxis.setMin(0);

        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
//        yAxis.setLabel("Tipos");
    }

    public void consultarReporte() {
        Calendar fechaF = Calendar.getInstance();
        Calendar fechaI = Calendar.getInstance();
        fechaF.setTime(fechaFinal);
        fechaI.setTime(fechaInicial);
        reporte = servicioReporte.getReporte(fechaI, fechaF);
        createModels();
    }

    public void consultarReporteCliente() {
        Calendar fechaF = Calendar.getInstance();
        Calendar fechaI = Calendar.getInstance();
        fechaF.setTime(fechaFinalCliente);
        fechaI.setTime(fechaInicialCliente);
        reporte = servicioReporte.getReporteCliente("", fechaI, fechaF);
        createModels();
    }

    public List<SelectItem> getItems() {
        return items;
    }

    public void setItems(List<SelectItem> items) {
        this.items = items;
    }

}
