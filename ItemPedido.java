package com.mycompany.main;

public class ItemPedido {

    private String nombreProducto;
    private String descripcionOpcion;
    private double precioUnitario;
    private int cantidad;

    public ItemPedido(String nombreProducto, String descripcionOpcion, double precioUnitario, int cantidad) {
        this.nombreProducto = nombreProducto;
        this.descripcionOpcion = descripcionOpcion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getDescripcionOpcion() {
        return descripcionOpcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }
}
