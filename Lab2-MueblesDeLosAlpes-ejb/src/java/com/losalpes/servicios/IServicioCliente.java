/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioCliente.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.bos.Usuario;
import java.util.List;


/**
 *
 * @author G40
 */
public interface IServicioCliente {
    
    
    /**
     * Agrega un cliente al sistema
     * @param usuario Nuevo usuario cliente
     */
    public void agregarCliente(Usuario cliente);
    
    /**
     * Lista los clientes del sistema
     * @return clientes Lista de clientes
     */
    public List<Usuario> darClientes();
    
    /**
     * Borra un  cliente de la lista
     * @param IdCliente Identificador cliente
     */
    public void borrarCliente(String cedula);
    
    /**
     * Actualiza un cliente
     * @param cliente
     */
    public void actualizarCliente(Usuario cliente);
    
    
    /**
     * Consuta un cliente del sistema
     * @param IdCliente
     * @return Usuario Cliente del sistema
     */
    public Usuario consultarCliente(String cedulaCliente);
}
