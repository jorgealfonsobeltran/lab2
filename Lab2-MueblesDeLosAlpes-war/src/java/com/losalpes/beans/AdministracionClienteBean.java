/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.bos.TipoUsuario;
import com.losalpes.bos.Usuario;
import com.losalpes.servicios.IServicioCliente;
import com.losalpes.servicios.ServicioClienteMock;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author G40
 */
@ManagedBean(name = "administracionClienteBean")
@SessionScoped
public class AdministracionClienteBean implements Serializable {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Representa un nuevo cliente
     */
    private DataTable dataTable;
    private Usuario cliente;

    private Usuario usuarioEliminado;

    @ManagedProperty(value = "#{applicationBean}")
    private ApplicationBean applicationBean;

    /**
     * Relación con la interfaz que provee los servicios necesarios de la
     * administracion de clientes.
     */
    private final IServicioCliente administracionCliente;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor de la clase principal
     */
    public AdministracionClienteBean() {
        cliente = new Usuario();
        administracionCliente = new ServicioClienteMock();
        usuarioEliminado = new Usuario();

    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    /**
     * Obtener un cliente
     *
     * @return cliente Objeto Usuario
     */
    public Usuario getCliente() {
        return cliente;
    }

    /**
     * Asignar un cliente
     *
     * @param cliente Objeto Usuario
     */
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve una lista con todos los clientes del sistema
     *
     * @return clientes Lista de Usuarios tipo cliente
     */
    public List<Usuario> getClientes() {
        for (Usuario usuario : applicationBean.getUsuarios()) {
            if (usuario.getCedula().equals("987654321") && usuario.getNombre().equals("admin")) {
                applicationBean.getUsuarios().remove(usuario);
                break;
            }
        }
        return applicationBean.getUsuarios();
    }

    /**
     * Elimina la información del cliente
     */
    public void limpiar() {
        cliente = new Usuario();
    }

    /**
     * Agrega un nuevo cliente al sistema
     */
    public void agregarCliente() {
        administracionCliente.agregarCliente(cliente);
        cliente = new Usuario();
    }

    /**
     * Borra un cliente del sistema
     */
    public void borrarCliente() {
        usuarioEliminado = (Usuario) dataTable.getRowData();
        administracionCliente.borrarCliente(usuarioEliminado.getCedula());
        cliente = new Usuario();
        applicationBean.getUsuarios().remove(usuarioEliminado);
    }

    /**
     * Actualiza los datos de un cliente del sistema
     */
    public void actualizarCliente() {
        administracionCliente.actualizarCliente(cliente);
        cliente = new Usuario();
    }

    /**
     * Actualiza los datos de un cliente del sistema
     */
    public void consultarCliente() {
        Usuario usuario = (Usuario) dataTable.getRowData();
        cliente = administracionCliente.consultarCliente(usuario.getCedula());
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public void agregarUsuarioAppication() {
        cliente.setTipo(TipoUsuario.CLIENTE);
        applicationBean.getUsuarios().add(cliente);
    }

    public ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    public void setApplicationBean(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;
    }

}
