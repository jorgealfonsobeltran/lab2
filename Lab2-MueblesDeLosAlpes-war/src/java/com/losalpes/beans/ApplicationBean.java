/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.bos.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author G40
 */
@ManagedBean(name ="applicationBean")
@ApplicationScoped
public class ApplicationBean  implements Serializable{

    private List<Usuario> usuarios;

    /**
     * Creates a new instance of ApplicationBean
     */
    public ApplicationBean() {

    }

    @PostConstruct
    public void inicializar() {
        usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    

}
