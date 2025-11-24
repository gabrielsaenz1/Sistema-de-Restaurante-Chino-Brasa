package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private List<ItemPedido> items;
    private EstadoPedido estado;

    //  NUEVOS CAMPOS AGREGADOS
    private String metodoPago;        // Yape / Plin / Efectivo / Tarjeta
    private String tipoComprobante;   // Boleta / Factura

    public Pedido(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.estado = EstadoPedido.PENDIENTE;

        // valores iniciales
        this.metodoPago = "No registrado";
        this.tipoComprobante = "No registrado";
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void agregarItem(ItemPedido item) {
        items.add(item);
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    // -------------------------
    //     CALCULAR TOTAL
    // -------------------------
    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    // -------------------------
    //      GETTERS/SETTERS
    // -------------------------

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
}


