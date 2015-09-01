/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.bos.Mueble;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Your Name
 */
public class ServicioCatalogoClienteMock implements ICatalogoCliente {

    @Override
    public void escribirCompra(Mueble m) {
        try {
            FileWriter fileWriter =  new FileWriter(new File("com/resource/compra.txt"),true);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(   m.getReferencia() + "," + m.getNombre() + "," + m.getTipo() + "," + m.getDescripcion()+"\n");
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
