/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.bos.Mueble;
import com.losalpes.servicios.ICatalogoCliente;
import com.losalpes.servicios.IServicioCatalogo;
import com.losalpes.servicios.ServicioCatalogoClienteMock;
import com.losalpes.servicios.ServicioCatalogoMock;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Your Name
 */
@ManagedBean(name = "catalogoCliente")
@SessionScoped
public class CatalogoClienteBean implements Serializable {

    private final IServicioCatalogo catalogo;
    private List<Mueble> mueblesDisponibles;
    private List<Mueble> mueblesComprados;
    private DataTable dataTable;
    private DataTable dataTableComprados;
    private final ICatalogoCliente catalogoCliente;
    private boolean mostrarCompra;
    private Mueble muebleCompra;
    private Mueble muebleEliminado;

    @PostConstruct
    public void iniciar() {
        mueblesComprados = new ArrayList<>();
    }

    /**
     * Creates a new instance of CatalogoClienteBean
     */
    public CatalogoClienteBean() {
        catalogo = new ServicioCatalogoMock();
        catalogoCliente = new ServicioCatalogoClienteMock();
        mueblesDisponibles = catalogo.darMuebles();

    }

    public List<Mueble> getMueblesDisponibles() {
        return mueblesDisponibles;
    }

    public void setMueblesDisponibles(List<Mueble> mueblesDisponibles) {
        this.mueblesDisponibles = mueblesDisponibles;
    }

    public void agregarMuebleCarro() {
        Mueble mueble = (Mueble) dataTable.getRowData();
        mueblesComprados.add(mueble);
    }

    public void eliminarMuebleCarro() {
        Mueble mueble = (Mueble) dataTable.getRowData();
        mueblesComprados.remove(mueble);
    }

    public List<Mueble> getMueblesComprados() {
        return mueblesComprados;
    }

    public void setMueblesComprados(List<Mueble> mueblesComprados) {
        this.mueblesComprados = mueblesComprados;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public DataTable getDataTableComprados() {
        return dataTableComprados;
    }

    public void setDataTableComprados(DataTable dataTableComprados) {
        this.dataTableComprados = dataTableComprados;
    }

    public boolean isMostrarCompra() {
        return mostrarCompra;
    }

    public void setMostrarCompra(boolean mostrarCompra) {
        this.mostrarCompra = mostrarCompra;
    }

    public void mostrarCompraCarro() {
        mostrarCompra = true;
    }

    public void mostrarMueblesDisponibles() {
        mostrarCompra = false;
    }

    public Mueble getMuebleCompra() {
        return muebleCompra;
    }

    public void setMuebleCompra(Mueble muebleCompra) {
        this.muebleCompra = muebleCompra;
        mueblesComprados.add(muebleCompra);
    }

    public Mueble getMuebleEliminado() {
        return muebleEliminado;
    }

    public void setMuebleEliminado(Mueble muebleEliminado) {
        this.muebleEliminado = muebleEliminado;
        mueblesComprados.remove(muebleEliminado);
    }
    
    
}
