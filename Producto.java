package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    private static int contadorId = 1;

    private int id;
    private String nombre;
    private List<OpcionProducto> opciones;

    public Producto(String nombre) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.opciones = new ArrayList<>();
    }

    public void agregarOpcion(String descripcion, double precio) {
        opciones.add(new OpcionProducto(descripcion, precio));
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<OpcionProducto> getOpciones() {
        return opciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

