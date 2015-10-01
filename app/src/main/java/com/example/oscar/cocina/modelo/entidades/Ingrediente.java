package com.example.oscar.cocina.modelo.entidades;

import java.io.Serializable;

/**
 * Created by manana on 21/09/15.
 */
public class Ingrediente implements Serializable{

    private long id;
    private String nombre;
    private String medida;
    private String cantidad;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
