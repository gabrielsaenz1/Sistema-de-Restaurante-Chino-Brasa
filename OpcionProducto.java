package com.mycompany.main;

public class OpcionProducto {

    private String descripcion;
    private double precio;

    public OpcionProducto(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}
