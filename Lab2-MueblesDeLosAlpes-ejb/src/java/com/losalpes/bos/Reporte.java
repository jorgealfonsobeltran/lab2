/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.bos;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camilo Marroquin
 */
public class Reporte {
    
    private List<InformeDiario> informeDiarios;

    public List<InformeDiario> getInformeDiarios() {
        return informeDiarios;
    }

    public void setInformeDiarios(List<InformeDiario> informeDiarios) {
        this.informeDiarios = informeDiarios;
    }
    
    public void addInformeDiarios(InformeDiario informeDiarios) {
        
        this.informeDiarios.add(informeDiarios);
    }
    
    public Reporte(){
        this.informeDiarios = new ArrayList<>();
    }
}

