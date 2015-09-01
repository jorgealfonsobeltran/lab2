/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * ServicioSeguridad.java Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación Licenciado bajo el
 * esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.servicios;

import com.losalpes.bos.TipoUsuario;
import com.losalpes.bos.Usuario;
import com.losalpes.excepciones.AutenticacionException;
import java.util.ArrayList;

/**
 * Clase que se encarga de la autenticación de un usuario en el sistema
 *
 */
public class ServicioSeguridadMock implements IServicioSeguridad {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Arreglo con los usuarios del sistema
     */
    private ArrayList<Usuario> usuarios;

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     *
     */
    public ServicioSeguridadMock() {
        //Inicializa el arreglo que contiene los usuarios
        usuarios = new ArrayList<>();

        //Agrega usuarios al sistema
        
//        usuarios.add(new Usuario("123456789", "client", "clientclient", TipoUsuario.CLIENTE));
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     * Registra el ingreso de un usuario al sistema.
     *
     * @param nombre Login del usuario que quiere ingresar al sistema.
     * @param contraseña Contraseña del usuario que quiere ingresar al sistema.
     * @return usuario Retorna el objeto que contiene la información del usuario
     * que ingreso al sistema.
     */
    @Override
    public Usuario login(String nombre, String contraseña) throws AutenticacionException {
        Usuario u = null;
        
        boolean termino = false;

        for (int i = 0; i < this.usuarios.size() && !termino; i++) {
            u = this.usuarios.get(i);

            if (u.getNombre().equals(nombre) && u.getContraseña().equals(contraseña)) {
                termino = true;
            } else if (i == this.usuarios.size() - 1) {
                throw new AutenticacionException("Usuario Inválido");
            }
        }

        return u;
    }

    @Override
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = new ArrayList<>();        
        this.usuarios = usuarios;
        usuarios.add(new Usuario("987654321", "admin", "adminadmin", TipoUsuario.ADMINISTRADOR));
    }

}
