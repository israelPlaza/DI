/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import com.opencsv.bean.CsvBindByPosition;
/**
 *
 * @author isra
 */
public class coche {
    
    
    @CsvBindByPosition(position = 0)
    private String marca;

    @CsvBindByPosition(position = 1)
    private String modelo;

    @CsvBindByPosition(position = 2)
    private String potencia;

    @CsvBindByPosition(position = 3)
    private String precio;
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
