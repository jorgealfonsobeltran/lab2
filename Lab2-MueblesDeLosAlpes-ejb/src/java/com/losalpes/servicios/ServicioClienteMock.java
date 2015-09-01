/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioCatalogoMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.bos.Usuario;
import com.losalpes.bos.TipoUsuario;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author G40
 */

/**
 * Implementacion de los servicios del catalogo de clientes que tiene el sistema
 * 
 */

public class ServicioClienteMock implements IServicioCliente{

    
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Arreglo con los clientes del sistema
     */
    private ArrayList<Usuario> clientes;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioClienteMock()
    {

        //Inicializa el arreglo de los clientes
        clientes=new ArrayList<Usuario>();

        //Agrega los clientes del sistema
        clientes.add(new Usuario("80987654","cliente1","clientecliente1",TipoUsuario.CLIENTE));
        clientes.add(new Usuario("109876345","cliente2","clientecliente2",TipoUsuario.CLIENTE));
        clientes.add(new Usuario("19876543","cliente3","clientecliente3",TipoUsuario.CLIENTE));
        clientes.add(new Usuario("52342123","cliente4","clientecliente4",TipoUsuario.CLIENTE));
        clientes.add(new Usuario("53432678","cliente5","clientecliente5",TipoUsuario.CLIENTE));
        clientes.add(new Usuario("98745671","cliente6","clientecliente6",TipoUsuario.CLIENTE));
        
    }
    
    
    /**
     * Agrega un cliente al sistema validando que no tenga una cedula que 
     * ya haya sido asignada a otro usuario
     * @param cliente 
     */
    @Override
    public void agregarCliente(Usuario cliente) {
        boolean existe=false;
        for(Usuario clienteLista: clientes){
            if(clienteLista.getCedula().equals(cliente.getCedula())){
                existe=true;
            }
        }
        if(existe==false){
            this.clientes.add(new Usuario(cliente.getCedula(),cliente.getNombre(),cliente.getContraseña(),TipoUsuario.CLIENTE));
        }
        else{
            actualizarCliente(cliente);
        }
    }

    /**
     * Retorna una lista con los usuarios tipo cliente del sistema
     * @return clientes Lista con los clientes del sistema
     */
    @Override
    public List<Usuario> darClientes() {
        return clientes;
    }

    /**
     * Elimina un cliente del sistema
     * @param cedula
     */
    @Override
    public void borrarCliente(String cedula) {
        int contador=0;
        for(Usuario clienteLista: this.clientes){
            if(clienteLista.getCedula().equals(cedula)){
                this.clientes.remove(contador);
                break;
            }
            contador++;
        }
    }

    @Override
    public void actualizarCliente(Usuario cliente) {
        for(Usuario clienteLista : this.clientes){
            if(clienteLista.equals(cliente)){
                clienteLista.setContraseña(cliente.getContraseña());
                clienteLista.setNombre(cliente.getNombre());
                clienteLista.setTipo(cliente.getTipo());
            }
        }
    }

    @Override
    public Usuario consultarCliente(String cedulaCliente) {
        Usuario clienteLocal=new Usuario();
        for(Usuario clienteLista : this.clientes){
            if(clienteLista.getCedula().equals(cedulaCliente)){
                 clienteLocal=clienteLista;
                 break;
            }
        }
        return clienteLocal;
    }
    
}
