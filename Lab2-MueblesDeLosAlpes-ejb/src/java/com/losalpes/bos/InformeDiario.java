/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.bos;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Camilo Marroquin
 */
public class InformeDiario {
    Calendar fecha;
    
    int mueblesTotal;
            
    int mueblesInterior;
    
    int mueblesExterior;
    
    double valorTotal;
     
    double valorInterior;
    
    double valorExterior;
    
    
    public InformeDiario(Calendar fecha, int mueblesInterior, int mueblesExterior, double valorInterior, double valorExterior){
        this.fecha = fecha;
        this.mueblesInterior = mueblesInterior;
        this.mueblesExterior = mueblesExterior;
        this.mueblesTotal = mueblesInterior + mueblesExterior;
        this.valorInterior = valorInterior;
        this.valorExterior = valorExterior;
        this.valorTotal = valorInterior + valorExterior;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double ValorTotal) {
        this.valorTotal = ValorTotal;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getMueblesTotal() {
        return mueblesTotal;
    }

    public void setMueblesTotal(int mueblesTotal) {
        this.mueblesTotal = mueblesTotal;
    }

    public int getMueblesInterior() {
        return mueblesInterior;
    }

    public void setMueblesInterior(int MueblesInterior) {
        this.mueblesInterior = MueblesInterior;
    }

    public int getMueblesExterior() {
        return mueblesExterior;
    }

    public void setMueblesExterior(int MueblesExterior) {
        this.mueblesExterior = MueblesExterior;
    }

    public double getValorInterior() {
        return valorInterior;
    }

    public void setValorInterior(double ValorInterior) {
        this.valorInterior = ValorInterior;
    }

    public double getValorExterior() {
        return valorExterior;
    }

    public void setValorExterior(double ValorExterior) {
        this.valorExterior = ValorExterior;
    }
}
